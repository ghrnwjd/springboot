package com.ghrnwjd.httpresponsecode.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseDTO<T> {

    T data;
    int code;
    HttpStatus response;
    String comment;

    @Builder
    public ResponseDTO(int code, HttpStatus response, String comment) {
        this.code = code;
        this.response = response;
        this.comment = comment;
    }

}
