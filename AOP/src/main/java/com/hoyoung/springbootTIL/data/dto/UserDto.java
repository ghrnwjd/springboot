package com.hoyoung.springbootTIL.data.dto;


import com.hoyoung.springbootTIL.data.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // jackson 라이브러리는 빈 생성자가 없는 모델을 생성하는 방법을 모름.
public class UserDto {

      @NotNull(message = "유저네임 키 값이 없습니다.")
      @NotBlank(message = "유저네임을 입력하세요.")
      @Size(max = 20, message = "유저네임 길이를 초과하였습니다.")
      private String userName;

      @NotNull(message = "비밀번호가 없습니다.")
      private String userPassword;

      @Email(message = "이메일형식이 올바르지 않습니다.")
      private String userEmail;

      private String userPhoneNumber;

      public User toEntity() {
            return User.builder()
                    .userName(this.userName)
                    .userPassword(this.userPassword)
                    .userEmail(this.userEmail)
                    .userPhoneNumber(this.userPhoneNumber)
                    .build();
      }

      @Override
      public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("유저이름: ").append(this.userName).append("\n")
                    .append("유저 패스워드: ").append(this.userPassword).append("\n")
                    .append("유저 이메일: ").append(this.userEmail);

            return sb.toString();
      }

      public UserDto(String userName, String userPassword, String userEmail, String userPhoneNumber) {
            this.userName = userName;
            this.userPassword = userPassword;
            this.userEmail = userEmail;
            this.userPhoneNumber = userPhoneNumber;
      }
}
