package com.hoyoung.springbootTIL.controller.api;


import com.hoyoung.springbootTIL.data.dto.CommonResponseDto;
import com.hoyoung.springbootTIL.data.dto.UserDto;
import com.hoyoung.springbootTIL.data.entity.User;
import com.hoyoung.springbootTIL.data.repository.UserRepository;
import com.hoyoung.springbootTIL.service.HttpMethodService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/http")
public class HttpMethodController {
      private final HttpMethodService httpMethodService;
      public HttpMethodController( HttpMethodService httpMethodService) {
            this.httpMethodService = httpMethodService;
      }

      @GetMapping("/user/{userName}")
      public CommonResponseDto<User> getUser(@PathVariable("userName") String username) {
            System.out.println("username: "+ username);

            User user = httpMethodService.findUser(username);

            return new CommonResponseDto< > (HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                    user);
      }

      @PostMapping("/user")
      public CommonResponseDto saveUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
            httpMethodService.saveUser(userDto);
            return new CommonResponseDto<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase());
      }

      @PutMapping("/user/{userName}")
      public CommonResponseDto<User> updateUser(@PathVariable("userName") String username,
                                                @RequestParam String userEmail, @RequestParam String userPassword) {

            User user = httpMethodService.updateUser(username, userEmail, userPassword);

            return new CommonResponseDto<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                    user);
      }

      @DeleteMapping("/user/{userName}")
      public CommonResponseDto deleteUser(@PathVariable("userName") String username) {
            httpMethodService.deleteUser(username);
            return new CommonResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
      }
}
