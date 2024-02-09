package ru.sharphurt.videohosting.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ControllerSuccessResponse {

    public static final ControllerSuccessResponse SuccessResponse = ControllerSuccessResponse.builder().success(true).build();

    private Boolean success;
}
