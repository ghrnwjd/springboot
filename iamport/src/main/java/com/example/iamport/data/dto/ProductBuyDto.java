package com.example.iamport.data.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProductBuyDto {

      private int boardId;
      private String cellerName;
      private String productPrice;
      private String buyerNumber;
      private String productName;

}
