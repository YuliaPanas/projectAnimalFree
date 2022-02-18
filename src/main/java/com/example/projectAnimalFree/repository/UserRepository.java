package com.example.projectAnimalFree.repository;

import com.example.projectAnimalFree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    boolean existsByEmail(String email);

    User findByEmail(String username);








}




