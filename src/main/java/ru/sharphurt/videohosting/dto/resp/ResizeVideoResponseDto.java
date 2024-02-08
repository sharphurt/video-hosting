package ru.sharphurt.videohosting.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResizeVideoResponseDto {
    private Boolean success;
}
