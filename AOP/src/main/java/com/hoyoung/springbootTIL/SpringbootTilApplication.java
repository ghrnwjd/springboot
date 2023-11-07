package com.hoyoung.springbootTIL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringbootTilApplication extends SpringBootServletInitializer {

      // jar -> war 패키징으로 변경하기 위한 설정
      @Override
      protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(SpringbootTilApplication.class);
      }

      public static void main(String[] args) {
            SpringApplication.run(SpringbootTilApplication.class, args);
      }

}
