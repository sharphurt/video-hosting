package ru.sharphurt.videohosting.service.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sharphurt.videohosting.dto.resp.ControllerSuccessResponse;
import ru.sharphurt.videohosting.service.filesystem.DeleteFromStorageService;
import ru.sharphurt.videohosting.service.sql.DeleteVideoSqlService;
import ru.sharphurt.videohosting.service.sql.GetVideoInformationSqlService;

import java.util.UUID;

import static ru.sharphurt.videohosting.constants.AliasConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteVideoControllerService {

    private final DeleteVideoSqlService deleteSqlService;
    private final DeleteFromStorageService deleteFromStorageService;
    private final GetVideoInformationSqlService informationSqlService;

    public ControllerSuccessResponse call(String id) {
        log.info(LOG_DELETE_VIDEO_REQUEST_RECEIVED.formatted(id));

        var uuid = UUID.fromString(id);
        var videoInformation = informationSqlService.getVideoInformation(uuid);

        deleteFromStorageService.delete(videoInformation);
        deleteSqlService.delete(uuid);
        log.info(LOG_DELETE_VIDEO_REQUEST_PROCESSED.formatted(id));

        return ControllerSuccessResponse.SuccessResponse;
    }
}
