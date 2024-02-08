package ru.sharphurt.videohosting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "video")
public class VideoEntity {

    @Id
    private UUID id;
    private String filename;
    private String path;
    private String extension;
    private Boolean processing = false;
    private Boolean processingSuccess = null;

    @Override
    public String toString() {
        return "VideoEntity {\n" +
                "   id: %s, \n".formatted(id) +
                "   path: %s, \n".formatted(path) +
                "   filename: %s, \n".formatted(filename) +
                "   extension: %s, \n".formatted(extension) +
                "   processing: %s, \n".formatted(processing) +
                "   processingSuccess: %s, \n".formatted(processingSuccess) +
                "}";
    }
}
