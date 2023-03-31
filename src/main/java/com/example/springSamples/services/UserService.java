package com.example.springSamples.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.springSamples.entities.UserEntity;
import com.example.springSamples.forms.UserForm;
import com.example.springSamples.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void signup(UserForm userForm) {
        repository.save(new UserEntity(userForm));
    }

    public UserEntity findById(long id) {
        return repository.findById(id);
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

}
