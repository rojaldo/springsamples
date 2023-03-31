package com.example.springSamples.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springSamples.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findById(long id);
    List<UserEntity> findAll();
}
