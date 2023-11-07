package com.hoyoung.springbootTIL.controller.api;


import com.hoyoung.springbootTIL.data.dto.CommonResponseDto;
import com.hoyoung.springbootTIL.data.dto.UserDto;
import com.hoyoung.springbootTIL.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserAPIController {
      private UserService userService;

      public UserAPIController(UserService userService) {
            this.userService = userService;
      }


      @PostMapping("/user/save")
      public CommonResponseDto<?> 유저회원가입(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
            userService.saveUser(userDto);
            return new CommonResponseDto<>(HttpStatus.CREATED.value(),
                    HttpStatus.CREATED.getReasonPhrase(), "회원가입 완료");
      }

      @GetMapping("/user/dto")
      public CommonResponseDto<UserDto> 유저정보출력DTO(@Valid UserDto userDTO, BindingResult bindingResult) {
            return new CommonResponseDto<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                    userDTO);
      }

      @GetMapping("/user/query")
      public CommonResponseDto<String> 유저정보출력쿼리(@RequestParam Map<String, String> params) {
            StringBuilder sb = new StringBuilder();

            params.entrySet().forEach(param -> {
                  sb.append(param.getKey() + ": " + param.getValue() + "\n");
            });

            return new CommonResponseDto<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                    sb.toString());
      }
}
