package com.hoyoung.springbootTIL.controller.api;


import com.hoyoung.springbootTIL.data.dto.ResponseDTO;
import com.hoyoung.springbootTIL.data.dto.UserDTO;
import com.hoyoung.springbootTIL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserAPIController {


    private UserService userService;

    @Autowired
    public UserAPIController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user/save")
    public ResponseDTO 유저회원가입 (@RequestBody UserDTO userDTO) {

        return userService.saveUser(userDTO);
    }
    @GetMapping("/user")
    public String 유저정보출력(UserDTO userDTO) {
        return userDTO.toString();
    }
}
