package com.example.projectAnimalFree.repository;

import com.example.projectAnimalFree.entity.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, Long> {

}
