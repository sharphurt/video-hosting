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
    private String title;
    private String filename;
    private Boolean processing = false;
    private Boolean processingSuccess = null;
}
