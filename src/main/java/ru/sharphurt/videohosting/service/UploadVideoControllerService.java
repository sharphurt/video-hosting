package ru.sharphurt.videohosting.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.sharphurt.videohosting.dto.resp.UploadVideoResponseDto;
import ru.sharphurt.videohosting.service.filesystem.impl.FileSystemStorageServiceImpl;
import ru.sharphurt.videohosting.service.sql.UploadVideoSqlService;

import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_VIDEO_UPLOAD_REQUEST_PROCESSED;
import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_VIDEO_UPLOAD_REQUEST_RECEIVED;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadVideoControllerService {

    private final FileSystemStorageServiceImpl fileSystemStorageService;
    private final UploadVideoSqlService uploadVideoSqlService;

    public UploadVideoResponseDto call(MultipartFile file) {
        log.info(LOG_VIDEO_UPLOAD_REQUEST_RECEIVED.formatted(file.getOriginalFilename()));

        var result = fileSystemStorageService.save(file);
        log.info(result.toString());

        uploadVideoSqlService.uploadFileInformation(result);
        log.info(LOG_VIDEO_UPLOAD_REQUEST_PROCESSED);

        return UploadVideoResponseDto.builder().id(result.getId()).build();
    }
}
