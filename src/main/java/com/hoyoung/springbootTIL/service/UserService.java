package com.hoyoung.springbootTIL.service;


import com.hoyoung.springbootTIL.data.dto.CommonResponseDto;
import com.hoyoung.springbootTIL.data.dto.UserDto;
<<<<<<< HEAD
import com.hoyoung.springbootTIL.data.entity.User;
import com.hoyoung.springbootTIL.data.repository.UserRepository;
import jakarta.transaction.Transactional;
=======
import com.hoyoung.springbootTIL.data.repository.UserRepository;
>>>>>>> 0ce1765ee3b1026126e3187e9e39c4ae935c76b8
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {


      private UserRepository userRepository;

      @Autowired
      public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
      }

      public void saveUser(UserDto userDTO) {
            userRepository.save(userDTO.toEntity());
      }

}
