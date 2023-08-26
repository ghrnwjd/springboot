package com.hoyoung.springbootTIL.data.dto;


import com.hoyoung.springbootTIL.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserDTO {
    private String userName;
    private String userPassword;
    private String userEmail;

    public User toEntity() {

        return User.builder()
                .userName(this.userName)
                .userPassword(this.userPassword)
                .userEmail(this.userEmail)
                .userPhoneNumber(null)
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
}
