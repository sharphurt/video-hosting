package ru.sharphurt.videohosting.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ControllerErrorResponse {

    private String error;

    public static ControllerErrorResponse fromException(RuntimeException e) {
        return ControllerErrorResponse.builder().error(e.getMessage()).build();
    }
}
