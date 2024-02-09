package ru.sharphurt.videohosting.service;

import lombok.SneakyThrows;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.sharphurt.videohosting.exceptions.VideoProcessingException;
import ru.sharphurt.videohosting.service.ffmpeg.ResizeVideoFfmpegService;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sharphurt.videohosting.sample.ResizeVideoDtoSample.resizeVideoRequestDto;
import static ru.sharphurt.videohosting.sample.VideoFileInformationSample.videoFileInformationDto;

@ExtendWith(MockitoExtension.class)
public class ResizeVideoFfmpegServiceTest {

    @InjectMocks
    private ResizeVideoFfmpegService service;

    @BeforeEach
    @SneakyThrows
    public void setUp() {
        var ffmpeg = new FFmpeg("C:\\Program Files\\ffmpeg\\bin\\ffmpeg.exe");
        var ffprobe = new FFprobe("C:\\Program Files\\ffmpeg\\bin\\ffprobe.exe");

        ReflectionTestUtils.setField(service, "ffmpegExecutor", new FFmpegExecutor(ffmpeg, ffprobe));
    }

    @Test
    public void resizeVideo_successTest() {
        var path = getClass()
                .getClassLoader()
                .getResource("file.mp4")
                .getPath().substring(1);

        videoFileInformationDto.setPath(Path.of(path));

        var result = service.call(videoFileInformationDto, resizeVideoRequestDto);
        assertTrue(result);
    }

    @Test
    public void resizeVideo_exceptionTest() {
        videoFileInformationDto.setPath(Path.of("wrong/path"));
        assertThrows(VideoProcessingException.class, () -> service.call(videoFileInformationDto, resizeVideoRequestDto));
    }
}
