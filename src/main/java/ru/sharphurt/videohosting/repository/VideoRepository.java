package ru.sharphurt.videohosting.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sharphurt.videohosting.entity.VideoEntity;

import java.util.Optional;
import java.util.UUID;

public interface VideoRepository extends CrudRepository<VideoEntity, UUID> {

    VideoEntity save(VideoEntity entity);
    Optional<VideoEntity> findById(UUID uuid);
    void deleteById(UUID uuid);
}
