package ru.sharphurt.videohosting.service.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.service.sql.GetVideoInformationSqlService;

import java.util.UUID;

import static ru.sharphurt.videohosting.constants.AliasConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetVideoInformationControllerService {

    private final GetVideoInformationSqlService sqlService;

    public VideoFileInformationDto call(String id) {
        log.info(LOG_GET_INFORMATION_REQUEST_RECEIVED.formatted(id));

        var uuid = UUID.fromString(id);
        log.info(LOG_GET_INFORMATION_REQUEST_PROCESSED.formatted(id));

        return sqlService.getVideoInformation(uuid);
    }
}
