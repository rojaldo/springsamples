package com.example.springSamples.forms;

import jakarta.validation.constraints.Size;

public class UserForm {


    @Size(min = 2, max = 20)
    private String userLogin;
    private String userPassword;

    public UserForm() {
    }

    public UserForm(String userLogin, String userPassword, byte role) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
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
    
}
