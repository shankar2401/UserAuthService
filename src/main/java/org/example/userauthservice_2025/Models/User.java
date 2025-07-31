package org.example.userauthservice_2025.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends  BaseModel {
    private String name;
    private String password;
    private String email;
    private String phonenumber;
    @ManyToMany
    private List<Role> roles;
}
