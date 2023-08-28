package com.hoyoung.springbootTIL.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


// Exception을 낚아채기
// 어노테이션 두개를 사용함
@RestController
@ControllerAdvice
public class MyExceptionHandler {
      @ExceptionHandler(value = Exception.class)

      public String errorHandler(Exception e) {
            return e.getMessage();
      }

}
