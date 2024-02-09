package ru.sharphurt.videohosting.config;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FfmpegConfig {

    @Value("${ffmpeg.path}")
    private String ffmpegPath;

    @Value("${ffprobe.path}")
    private String ffprobePath;

    @Bean(name = "ffmpegExecutor")
    public FFmpegExecutor ffmpegExecutor() throws IOException {
        var ffmpeg = new FFmpeg(ffmpegPath);
        var ffprobe = new FFprobe(ffprobePath);

        return new FFmpegExecutor(ffmpeg, ffprobe);
    }
}
