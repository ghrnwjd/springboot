package com.hoyoung.springbootTIL.service;


import com.hoyoung.springbootTIL.data.dto.UserDto;
import com.hoyoung.springbootTIL.data.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
      private UserRepository userRepository;
      public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
      }
      public void saveUser(UserDto userDTO) {
            userRepository.save(userDTO.toEntity());
      }





}
