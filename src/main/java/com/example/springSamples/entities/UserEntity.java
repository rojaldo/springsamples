package com.example.springSamples.entities;

import com.example.springSamples.forms.UserForm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;

    @Size(min = 2, max = 20)
    private String userLogin;
    private String userPassword;
    private byte role;

    public UserEntity() {
    }

    public UserEntity(String userLogin, String userPassword, byte role) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.role = role;
    }

    public UserEntity(UserForm form) {
        this.userLogin = form.getUserLogin();
        this.userPassword = form.getUserPassword();
        this.role = 0;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }
    
}
