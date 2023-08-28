package com.hoyoung.springbootTIL.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int userId;

      @Column(nullable = false)
      private String userName;

      @Column(nullable = false)
      private String userPassword;

      @Column
      private String userEmail;

      @Column
      private String userPhoneNumber;

      @Builder
      public User(String userName, String userPassword, String userEmail, String userPhoneNumber) {
            this.userName = userName;
            this.userPassword = userPassword;
            this.userEmail = userEmail;
            this.userPhoneNumber = userPhoneNumber;
      }

}
