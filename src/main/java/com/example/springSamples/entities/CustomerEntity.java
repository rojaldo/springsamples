package com.example.springSamples.entities;

import java.util.List;

import org.aspectj.weaver.loadtime.Agent;

import com.example.springSamples.response.ICustomerResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity implements ICustomerResponse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public String lastName;
    @Column(name = "email")
    public String email;
    @Column(name = "age")
    public int age;

    @OneToMany(mappedBy = "customer")
    private List<SaleEntity> purchases;


    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "CustomerEntity [age=" + age + ", email=" + email + ", firstName=" + firstName + ", id=" + id
                + ", lastName=" + lastName + "]";
    }

}
