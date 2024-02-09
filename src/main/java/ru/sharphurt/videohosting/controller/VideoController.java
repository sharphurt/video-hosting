package ru.sharphurt.videohosting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "video-hosting", description = "Video hosting API: upload video, resize request, retreive info, delete request")
public class VideoController {

    private final UploadVideoControllerService uploadVideoService;
    private final ResizeVideoControllerService resizeVideoService;
    private final GetVideoInformationControllerService getInfoService;
    private final DeleteVideoControllerService deleteVideoService;

    @PostMapping
    @Operation(summary = "Upload a video on server",
            description = "Upload an .mp4 video file on server. The response is UUID object with unique id",
            tags = {"video-hosting", "upload"})
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UploadVideoResponseDto.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Video file is invalid or has unacceptable extension", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", description = "Video file cannot be saved in storage", content = {@Content(schema = @Schema())})
    public ResponseEntity<UploadVideoResponseDto> uploadVideo(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(uploadVideoService.call(file));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Resize video",
            description = "Resize video by id with width and height. Size are specified in the json in the body of the request",
            tags = {"video-hosting", "resize"})
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ControllerSuccessResponse.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Failed validation", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "404", description = "Video with specified id not found", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", description = "Video can not be processed: FFMPEG error", content = {@Content(schema = @Schema())})
    public ResponseEntity<ControllerSuccessResponse> patchFile(@PathVariable("id") String id, @RequestBody @Validated ResizeVideoRequestDto body) {
        return ResponseEntity.ok(resizeVideoService.call(id, body));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get video information",
            description = "Retreive an information about video: filename, id and processing status: current state and last result",
            tags = {"video-hosting", "get"})
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ControllerSuccessResponse.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", description = "Video with specified id not found", content = {@Content(schema = @Schema())})
    public ResponseEntity<VideoFileInformationDto> getInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(getInfoService.call(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete video",
            description = "Delete video from server and delete information about it",
            tags = {"video-hosting", "delete"})
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ControllerSuccessResponse.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", description = "Video with specified id not found", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", description = "File can not be deleted from storage", content = {@Content(schema = @Schema())})
    public ResponseEntity<ControllerSuccessResponse> deleteFile(@PathVariable("id") String id) {
        return ResponseEntity.ok(deleteVideoService.call(id));
    }
}
