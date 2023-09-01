package com.hoyoung.springbootTIL.data.dto;

import lombok.*;



@NoArgsConstructor
@Data
public class CommonResponseDto<T> {

      private int statusCode;
      private String status;
      private T data;


      public CommonResponseDto(int statusCode, String status) {
            this.statusCode = statusCode;
            this.status = status;
      }

      public CommonResponseDto(int statusCode, String status, T data) {
            this.statusCode = statusCode;
            this.status = status;
            this.data = data;
      }

}
