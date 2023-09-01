package com.hoyoung.springbootTIL.data.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int userId;

      @Column(nullable = false, name="name")
      private String userName;

      @Column(nullable = false, name = "password")
      private String userPassword;

      @Column(name = "email")
      private String userEmail;

      @Column(name = "phone")
      private String userPhoneNumber;

      @OneToMany
      @JoinColumn(name="name")
      List<Memo> memoList = new ArrayList<>();

      @Builder
      public User(String userName, String userPassword, String userEmail, String userPhoneNumber) {
            this.userName = userName;
            this.userPassword = userPassword;
            this.userEmail = userEmail;
            this.userPhoneNumber = userPhoneNumber;
      }

}
