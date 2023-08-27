package com.hoyoung.springbootTIL.data.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.net.http.HttpResponse;



@Builder
@Getter
@Setter
public class ResponseDTO {

    private HttpStatus httpStatus;
}
