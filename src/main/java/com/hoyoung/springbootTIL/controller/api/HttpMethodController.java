package com.hoyoung.springbootTIL.controller.api;


import com.hoyoung.springbootTIL.data.dto.CommonResponseDto;
import com.hoyoung.springbootTIL.data.dto.UserDto;
import com.hoyoung.springbootTIL.data.entity.User;
import com.hoyoung.springbootTIL.data.repository.UserRepository;
import com.hoyoung.springbootTIL.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/http")
public class HttpMethodController {
      private UserRepository userRepository;

      public HttpMethodController(UserRepository userRepository) {
            this.userRepository = userRepository;
      }

      @GetMapping("/user/{userName}")
      public CommonResponseDto<User> getUser(@PathVariable("userName") String username) {
            System.out.println("username: "+ username);

            return new CommonResponseDto< > (HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                    userRepository.findByUserName(username));
      }

      @PostMapping("/user")
      public CommonResponseDto<User> inputUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
            User user = userDto.toEntity();
            userRepository.save(user);
            return new CommonResponseDto<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                    user);
      }

      @PutMapping("/user/{userName}")
      @Transactional
      public CommonResponseDto<User> updateUser(@PathVariable("userName") String username,
                                                @RequestParam String userEmail, @RequestParam String userPassword) {

            User user = userRepository.findByUserName(username);
            user.setUserEmail(userEmail);
            user.setUserPassword(userPassword);

            return new CommonResponseDto<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                    user);
      }

      @DeleteMapping("/user/{userName}")
      public CommonResponseDto deleteUser(@PathVariable("userName") String username) {
            User user = userRepository.findByUserName(username);
            userRepository.delete(user);
            return new CommonResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
      }
}
