package ru.sharphurt.videohosting.dto.req;

import lombok.Builder;
import lombok.Data;
import ru.sharphurt.videohosting.validation.constraints.EvenNumber;

import javax.validation.constraints.Min;

@Data
@Builder
public class ResizeVideoRequestDto {

    @Min(20)
    @EvenNumber
    private Integer width;

    @Min(20)
    @EvenNumber
    private Integer height;
}

