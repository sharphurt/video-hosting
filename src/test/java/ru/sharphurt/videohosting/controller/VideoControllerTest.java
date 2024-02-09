package ru.sharphurt.videohosting.controller;

import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;

import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.sharphurt.videohosting.sample.MultipartFileSample.multipartFile_correct;
import static ru.sharphurt.videohosting.sample.ResizeVideoDtoSample.*;
import static ru.sharphurt.videohosting.sample.VideoFileInformationSample.*;


public class VideoControllerTest extends BaseSpringContextTest {

    @BeforeEach
    public void setUp() {
        var path = getClass()
                .getClassLoader()
                .getResource("file.mp4")
                .getPath().substring(1);

        videoFileInformationDto.setPath(Path.of(path));
    }

    @Test
    public void uploadVideo_successTest() throws Exception {
        mvc.perform(multipart("/file")
                        .file(multipartFile_correct))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").exists());
    }

    @Test
    public void resizeVideo_successTest() throws Exception {
        var response = mvc.perform(multipart("/file").file(multipartFile_correct))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var uuid = objectMapper.readValue(response, Map.class).get("id");
        var resizeRequestJson = objectMapper.writeValueAsString(resizeVideoRequestDto);

        mvc.perform(patch("/file/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resizeRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("success").value("true"));
    }

    @Test
    public void resizeVideo_notValidHeightTest() throws Exception {
        var response = mvc.perform(multipart("/file").file(multipartFile_correct))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var uuid = objectMapper.readValue(response, Map.class).get("id");
        var resizeRequestJson = objectMapper.writeValueAsString(resizeVideoRequestDto_smallHeight);

        mvc.perform(patch("/file/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resizeRequestJson))
                .andExpect(status().is(400))
                .andExpect(jsonPath("error").value("Validation errors: Height cannot be less then 20 pixels"));
    }

    @Test
    public void resizeVideo_notValidWidthTest() throws Exception {
        var response = mvc.perform(multipart("/file").file(multipartFile_correct))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var uuid = objectMapper.readValue(response, Map.class).get("id");
        var resizeRequestJson = objectMapper.writeValueAsString(resizeVideoRequestDto_smallWidth);

        mvc.perform(patch("/file/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resizeRequestJson))
                .andExpect(status().is(400))
                .andExpect(jsonPath("error").value("Validation errors: Width cannot be less then 20 pixels"));
    }

    @Test
    public void resizeVideo_notEvenWidthTest() throws Exception {
        var response = mvc.perform(multipart("/file").file(multipartFile_correct))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var uuid = objectMapper.readValue(response, Map.class).get("id");
        var resizeRequestJson = objectMapper.writeValueAsString(resizeVideoRequestDto_notEvenWidth);

        mvc.perform(patch("/file/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resizeRequestJson))
                .andExpect(status().is(400))
                .andExpect(jsonPath("error").value("Validation errors: Width should be even number"));
    }

    @Test
    public void resizeVideo_notEvenHeightTest() throws Exception {
        var response = mvc.perform(multipart("/file").file(multipartFile_correct))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var uuid = objectMapper.readValue(response, Map.class).get("id");
        var resizeRequestJson = objectMapper.writeValueAsString(resizeVideoRequestDto_notEvenHeight);

        mvc.perform(patch("/file/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resizeRequestJson))
                .andExpect(status().is(400))
                .andExpect(jsonPath("error").value("Validation errors: Height should be even number"));
    }

    @Test
    public void getInfo_successTest() throws Exception {
        var response = mvc.perform(multipart("/file").file(multipartFile_correct))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var uuid = objectMapper.readValue(response, Map.class).get("id").toString();
        var filename = FilenameUtils.getBaseName(multipartFile_correct.getOriginalFilename());
        var expectedResponse = VideoFileInformationDto.builder()
                .id(UUID.fromString(uuid))
                .filename(filename)
                .extension("mp4")
                .processing(false)
                .processingSuccess(null)
                .build();

        mvc.perform(get("/file/" + uuid))
                .andExpect(status().is(200))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    public void getInfoDuringFfmpegJob_successTest() throws Exception {
        var response = mvc.perform(multipart("/file").file(multipartFile_correct))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var uuid = objectMapper.readValue(response, Map.class).get("id").toString();
        var filename = FilenameUtils.getBaseName(multipartFile_correct.getOriginalFilename());
        var expectedResponse = VideoFileInformationDto.builder()
                .id(UUID.fromString(uuid))
                .filename(filename)
                .extension("mp4")
                .processing(true)
                .processingSuccess(null)
                .build();

        var resizeRequestJson = objectMapper.writeValueAsString(resizeVideoRequestDto);
        mvc.perform(patch("/file/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resizeRequestJson));
        mvc.perform(get("/file/" + uuid))
                .andExpect(status().is(200))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }
}
