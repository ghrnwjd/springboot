package com.hoyoung.springbootTIL.controller.api;


import com.hoyoung.springbootTIL.data.entity.User;
import com.hoyoung.springbootTIL.service.HttpMethodService;
import com.hoyoung.springbootTIL.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HttpMethodController.class)
public class HttpMethodControllerTest {

      @Autowired
      private MockMvc mockMvc;
      @MockBean
      HttpMethodService httpMethodService;

      @Test
      @DisplayName("GET Method : 유저 조회")
      void 유저정보출력() throws Exception {
            // given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
            given(httpMethodService.findUser("jung")).willReturn(
                    User.builder().userName("jung").
                            userEmail("hoyoung.wjd@gmail.com").
                            userPassword("1234").
                            userPhoneNumber("010-2002-3123").build()
            );

            String username = "jung";

            mockMvc.perform(
                    MockMvcRequestBuilders.get("/http/user/{username}", "jung"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.userName").exists())
                    .andExpect(jsonPath("$.data.userEmail").exists())
                    .andExpect(jsonPath("$.data.userPassword").exists())
                    .andExpect(jsonPath("$.data.userPhoneNumber").exists())
                    .andDo(print());
      }

      @Test
      @DisplayName("PUT Method : 유저 정보 수정")
      void 유저정보수정() throws Exception {
            given(httpMethodService.updateUser("jung", "hoyoung2@hufs.ac.kr", "2345"))
                    .willReturn(User.builder().
                            userName("jung").userEmail("hoyoung2@hufs.ac.kr")
                            .userPassword("2345").userPhoneNumber("010-2002-3123").build());

            String username = "jung";
            String email = "hoyoung2@hufs.ac.kr";
            String password = "2345";

            mockMvc.perform(
                    MockMvcRequestBuilders.put("/http/user/{username}", username)
                            .param("userEmail", email). param("userPassword", password))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.userName").exists())
                    .andExpect(jsonPath("$.data.userEmail").exists())
                    .andExpect(jsonPath("$.data.userPassword").exists())
                    .andExpect(jsonPath("$.data.userPhoneNumber").exists())
                    .andDo(print());

      }
}
