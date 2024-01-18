package com.ghrnwjd.httpresponsecode.data.entity;

import com.ghrnwjd.httpresponsecode.data.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Person(String name, String password, RoleType roleType) {
        this.name = name;
        this.password = password;
        this.roleType = roleType;
    }
}
