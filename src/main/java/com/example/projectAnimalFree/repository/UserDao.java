package com.example.projectAnimalFree.repository;

import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.entity.User;
//import com.example.projectAnimalFree.mapper.UserMapper;
import com.example.projectAnimalFree.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class UserDao {

    private final EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveUser(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        entityManager.persist(user);
    }

    public UserDto getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return UserMapper.toDto(user);
    }

}
