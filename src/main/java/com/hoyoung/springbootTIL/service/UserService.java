package com.hoyoung.springbootTIL.service;


import com.hoyoung.springbootTIL.data.dto.ResponseDTO;
import com.hoyoung.springbootTIL.data.dto.UserDTO;
import com.hoyoung.springbootTIL.data.entity.User;
import com.hoyoung.springbootTIL.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDTO saveUser(UserDTO userDTO) {

        userRepository.save(userDTO.toEntity());

        return ResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
