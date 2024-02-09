package ru.sharphurt.videohosting.dto.req;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sharphurt.videohosting.validation.constraints.EvenNumber;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResizeVideoRequestDto {

    @Min(value = 20, message = "Width cannot be less then 20 pixels")
    @EvenNumber(message = "Width should be even number")
    private Integer width;

    @Min(value = 20, message = "Height cannot be less then 20 pixels")
    @EvenNumber(message = "Height should be even number")
    private Integer height;
}

