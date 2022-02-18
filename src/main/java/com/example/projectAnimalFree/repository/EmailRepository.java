package com.example.projectAnimalFree.repository;

import com.example.projectAnimalFree.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
