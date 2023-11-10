package com.example.iamport.data.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberLoginDto {

      private String memberNumber;
      private String password;
}
