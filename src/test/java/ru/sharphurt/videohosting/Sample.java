package ru.sharphurt.videohosting;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class Sample {

    public static final byte[] sampleFileContent = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};

    public static final MultipartFile multipartFile_correct = new MockMultipartFile("correctfile.mp4", "correctfile.mp4", "video/mp4", sampleFileContent);
    public static final MultipartFile multipartFile_empty = new MockMultipartFile("emptyfile.mp4", "emptyfile.mp4", "video/mp4", new byte[0]);

    public static final MultipartFile multipartFile_unsupportedExtension = new MockMultipartFile("emptyfile.mp5", "emptyfile.mp5", "video/mp4", sampleFileContent);
    public static final MultipartFile multipartFile_noExtension = new MockMultipartFile("emptyfile", "emptyfile", "video/mp4", sampleFileContent);
    public static final MultipartFile multipartFile_doubleExtension = new MockMultipartFile("emptyfile.tar.gz", "emptyfile.tar.gz", "video/mp4", sampleFileContent);
}
