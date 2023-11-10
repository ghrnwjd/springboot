package com.example.iamport.data.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseDto<T> {
      int statusCode;
      T data;

      public CommonResponseDto(int statusCode) {
            this.statusCode = statusCode;
      }
}
