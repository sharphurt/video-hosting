package ru.sharphurt.videohosting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.sharphurt.videohosting.dto.resp.VideoUploadResponseDto;
import ru.sharphurt.videohosting.service.filesystem.FileSystemStorageServiceImpl;
import ru.sharphurt.videohosting.service.sql.UploadVideoSqlService;

import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_VIDEO_UPLOAD_REQUEST_PROCESSED;
import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_VIDEO_UPLOAD_REQUEST_RECEIVED;

@Slf4j
@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class VideoController {

    private final FileSystemStorageServiceImpl fileSystemStorageService;
    private final UploadVideoSqlService uploadVideoSqlService;

    @PostMapping
    public VideoUploadResponseDto uploadVideo(@RequestParam("file") MultipartFile file) {
        log.info(LOG_VIDEO_UPLOAD_REQUEST_RECEIVED.formatted(file.getOriginalFilename()));

        var result = fileSystemStorageService.save(file);
        uploadVideoSqlService.uploadFileInformation(result);

        log.info(LOG_VIDEO_UPLOAD_REQUEST_PROCESSED);

        return VideoUploadResponseDto.builder().id(result.getId()).build();
    }
}
