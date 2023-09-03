package com.hoyoung.springbootTIL.service;


import com.hoyoung.springbootTIL.data.dto.UserDto;
import com.hoyoung.springbootTIL.data.entity.User;
import com.hoyoung.springbootTIL.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HttpMethodService {

      private final UserRepository userRepository;

      public HttpMethodService(UserRepository userRepository) {
            this.userRepository = userRepository;
      }

      public User findUser(String userName) {
            return userRepository.findByUserName(userName);
      }

      public void saveUser(UserDto userDto){
            userRepository.save(userDto.toEntity());
      }

      @Transactional
      public User updateUser(String username, String email, String password) {

            User user = userRepository.findByUserName(username);
            user.setUserEmail(email);
            user.setUserPassword(password);

            return user;
      }

      public void deleteUser(String username) {
            User user = userRepository.findByUserName(username);
            userRepository.delete(user);
      }
}
