package ru.sharphurt.videohosting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.dto.req.ResizeVideoRequestDto;
import ru.sharphurt.videohosting.dto.resp.ControllerSuccessResponse;
import ru.sharphurt.videohosting.dto.resp.UploadVideoResponseDto;
import ru.sharphurt.videohosting.service.main.DeleteVideoControllerService;
import ru.sharphurt.videohosting.service.main.GetVideoInformationControllerService;
import ru.sharphurt.videohosting.service.main.ResizeVideoControllerService;
import ru.sharphurt.videohosting.service.main.UploadVideoControllerService;

@Slf4j
@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class VideoController {

    private final UploadVideoControllerService uploadVideoService;
    private final ResizeVideoControllerService resizeVideoService;
    private final GetVideoInformationControllerService getInfoService;
    private final DeleteVideoControllerService deleteVideoService;

    @PostMapping
    public UploadVideoResponseDto uploadVideo(@RequestParam("file") MultipartFile file) {
        return uploadVideoService.call(file);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ControllerSuccessResponse> patchFile(@PathVariable String id, @RequestBody @Validated ResizeVideoRequestDto body) {
        return ResponseEntity.ok(resizeVideoService.call(id, body));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoFileInformationDto> getInformation(@PathVariable String id) {
        return ResponseEntity.ok(getInfoService.call(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerSuccessResponse> deleteFile(@PathVariable String id) {
        return ResponseEntity.ok(deleteVideoService.call(id));
    }
}
