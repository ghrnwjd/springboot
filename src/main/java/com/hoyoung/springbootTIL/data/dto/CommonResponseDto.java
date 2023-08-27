package com.hoyoung.springbootTIL.data.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResponseDto<T> {

      private int statusCode;
      private String status;
      private T data;

}
