package ru.sharphurt.videohosting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class VideoHostingApplication {

    public static void main(String[] args) {
        var d = SpringVersion.getVersion();

        SpringApplication.run(VideoHostingApplication.class, args);
    }
}
