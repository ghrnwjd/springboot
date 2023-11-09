package com.example.iamport.data.dto;

import com.example.iamport.data.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDto {

      private String membername;
      private String memberNumber;
      private String password;

      public Member toEntity() {

            Member member = new Member();
            member.setMembername(this.getMembername());
            member.setPassword(this.getPassword());
            member.setMemberNumber(this.getMemberNumber());

            return member;
      }

}
