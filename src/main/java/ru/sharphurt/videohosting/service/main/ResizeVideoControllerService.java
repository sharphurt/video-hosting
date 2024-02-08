package ru.sharphurt.videohosting.service.main;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sharphurt.videohosting.dto.req.ResizeVideoRequestDto;
import ru.sharphurt.videohosting.dto.resp.ControllerSuccessResponse;
import ru.sharphurt.videohosting.exceptions.VideoProcessingException;
import ru.sharphurt.videohosting.service.ffmpeg.ResizeVideoFfmpegService;
import ru.sharphurt.videohosting.service.sql.GetVideoInformationSqlService;
import ru.sharphurt.videohosting.service.sql.UpdateVideoInformationSqlService;

import java.util.UUID;

import static ru.sharphurt.videohosting.constants.AliasConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResizeVideoControllerService {

    private final GetVideoInformationSqlService loadVideoInformationSqlService;
    private final ResizeVideoFfmpegService resizeVideoFfmpegService;
    private final UpdateVideoInformationSqlService updateVideoInformationSqlService;

    private final String serviceName = "resize-video-controller-service";

    public ControllerSuccessResponse call(String id, ResizeVideoRequestDto body) {
        log.info(LOG_RESIZE_VIDEO_REQUEST_RECEIVED.formatted(id));

        var uuid = UUID.fromString(id);
        var videoInformation = loadVideoInformationSqlService.getVideoInformation(uuid);

        onFfmpegStartJob(uuid);
        Observable.fromCallable(() -> resizeVideoFfmpegService.call(videoInformation, body))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(
                        result -> onFfmpegFinishJob(uuid, result),
                        throwable -> {
                            throw new VideoProcessingException(serviceName, videoInformation.getId().toString());
                        });

        log.info(LOG_RESIZE_VIDEO_REQUEST_PROCESSED.formatted(id));

        return ControllerSuccessResponse.SuccessResponse;
    }

    private void onFfmpegFinishJob(UUID uuid, Boolean result) {
        log.info(LOG_VIDEO_RESIZE_FINISH_JOB.formatted(uuid));
        updateVideoInformationSqlService.setCurrentProcessingStatus(uuid, false);
        updateVideoInformationSqlService.setLastProcessingResult(uuid, result);
    }

    private void onFfmpegStartJob(UUID uuid) {
        log.info(LOG_VIDEO_RESIZE_START_JOB.formatted(uuid));
        updateVideoInformationSqlService.setCurrentProcessingStatus(uuid, true);
    }
}
