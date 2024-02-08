package ru.sharphurt.videohosting.service.ffmpeg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.stereotype.Service;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.dto.req.ResizeVideoRequestDto;
import ru.sharphurt.videohosting.exceptions.VideoProcessingException;

import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResizeVideoFfmpegService {

    private final FFmpegExecutor ffmpegExecutor;
    private final FFprobe ffprobe;

    private final String serviceName = "resize-video-ffmpeg-service";

    public Boolean call(VideoFileInformationDto videoFile, ResizeVideoRequestDto resizeDto) {
        try {

            var path = videoFile.getPath();
            var fileInformation = ffprobe.probe(path.toString());

            var temp = videoFile.getId() + "_temp." + videoFile.getExtension();
            var newPath = videoFile.getPath().resolveSibling(temp);

            var task = new FFmpegBuilder()
                    .setInput(path.toString())
                    .overrideOutputFiles(true)
                    .addOutput(newPath.toString())
                    .setVideoResolution(resizeDto.getWidth(), resizeDto.getHeight())
                    .done();

            ffmpegExecutor.createJob(task).run();

            Files.deleteIfExists(path);
            Files.move(newPath, path, REPLACE_EXISTING);

            return true;
        } catch (Exception e) {
            throw new VideoProcessingException(serviceName, videoFile.getPath().toString());
        }
    }
}
