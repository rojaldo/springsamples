package com.example.springSamples.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CustomerForm {

    @Size(min = 2, max = 30)
    private String firstName;
    @Size(min = 2, max = 30)
    private String secondName;
    @Email
    private String email;
    
    @Positive
    @Min(18)
    @Max(100)
    private int age;

    public CustomerForm() {
    }

    public CustomerForm(String firstName, String secondName, String email, int age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CustomerForm [age=" + age + ", email=" + email + ", firstName=" + firstName + ", secondName="
                + secondName + "]";
    }
}
