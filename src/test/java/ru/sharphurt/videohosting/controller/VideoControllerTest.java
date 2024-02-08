package ru.sharphurt.videohosting.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.nio.file.Path;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.sharphurt.videohosting.sample.MultipartFileSample.multipartFile_correct;
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
    public void resizeVideo_notValidDataTest() throws Exception {
        var response = mvc.perform(multipart("/file").file(multipartFile_correct))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var uuid = objectMapper.readValue(response, Map.class).get("id");
        var resizeRequestJson = objectMapper.writeValueAsString(resizeVideoRequestDto_notValid);

        mvc.perform(patch("/file/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resizeRequestJson))
                .andExpect(status().is(400));
    }
}
