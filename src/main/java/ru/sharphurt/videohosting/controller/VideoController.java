package ru.sharphurt.videohosting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sharphurt.videohosting.dto.req.ResizeVideoRequestDto;
import ru.sharphurt.videohosting.dto.resp.ResizeVideoResponseDto;
import ru.sharphurt.videohosting.dto.resp.UploadVideoResponseDto;
import ru.sharphurt.videohosting.service.ResizeVideoControllerService;
import ru.sharphurt.videohosting.service.UploadVideoControllerService;

@Slf4j
@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class VideoController {

    private final UploadVideoControllerService uploadVideoService;
    private final ResizeVideoControllerService resizeVideoService;

    @PostMapping
    public UploadVideoResponseDto uploadVideo(@RequestParam("file") MultipartFile file) {
        return uploadVideoService.call(file);
    }

    @PatchMapping("/{id}")
    public ResizeVideoResponseDto patchFile(@PathVariable String id, @RequestBody ResizeVideoRequestDto body) {
        return resizeVideoService.call(id, body);
    }

}
