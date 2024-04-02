package com.example.userservice_3.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role extends  BaseModel {
    private String name;
}
