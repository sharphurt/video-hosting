package ru.sharphurt.videohosting.service.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.sharphurt.videohosting.dto.resp.UploadVideoResponseDto;
import ru.sharphurt.videohosting.service.filesystem.SaveToStorageService;
import ru.sharphurt.videohosting.service.sql.UploadVideoSqlService;

import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_UPLOAD_VIDEO_REQUEST_PROCESSED;
import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_UPLOAD_VIDEO_REQUEST_RECEIVED;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadVideoControllerService {

    private final SaveToStorageService storageService;
    private final UploadVideoSqlService sqlService;

    public UploadVideoResponseDto call(MultipartFile file) {
        log.info(LOG_UPLOAD_VIDEO_REQUEST_RECEIVED.formatted(file.getOriginalFilename()));

        var result = storageService.save(file);
        log.info(result.toString());

        sqlService.uploadFileInformation(result);
        log.info(LOG_UPLOAD_VIDEO_REQUEST_PROCESSED.formatted(result.getId()));

        return UploadVideoResponseDto.builder().id(result.getId()).build();
    }
}
