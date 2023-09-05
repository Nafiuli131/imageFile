package com.nafiul.imageUpload.repository;

import com.nafiul.imageUpload.entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageFile,Long> {

    @Query("select im from ImageFile im where im.fileName = :fileName")
    Optional<ImageFile> findByName(String fileName);
}
