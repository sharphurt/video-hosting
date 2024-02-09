package ru.sharphurt.videohosting.sample;

import org.springframework.mock.web.MockMultipartFile;

public class MultipartFileSample {

    public static final byte[] sampleFileContent = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};

    public static final MockMultipartFile multipartFile_correct = new MockMultipartFile("file", "correctfile.mp4", "video/mp4", sampleFileContent);
    public static final MockMultipartFile multipartFile_empty = new MockMultipartFile("file", "emptyfile.mp4", "video/mp4", new byte[0]);

    public static final MockMultipartFile multipartFile_unsupportedExtension = new MockMultipartFile("file", "emptyfile.mp5", "video/mp4", sampleFileContent);
    public static final MockMultipartFile multipartFile_noExtension = new MockMultipartFile("file", "emptyfile", "video/mp4", sampleFileContent);
    public static final MockMultipartFile multipartFile_doubleExtension = new MockMultipartFile("file", "emptyfile.tar.gz", "video/mp4", sampleFileContent);
}
