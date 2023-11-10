package com.example.iamport.data.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDto {

      private String username;
      private String title;
      private String content;
      private String productName;
      private int price;

}
