package com.example.iamport.data.entity;


import com.example.iamport.data.dto.same.MemberSameDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "MEMBER_ID")
      private int id;

      @Column(nullable = false)
      private String membername;

      @Column(nullable = false)
      private String memberNumber;

      @Column(nullable = false)
      private String password;

      @Column
      private String phoneNumber;

      @Column
      private int account;

      @OneToMany(mappedBy = "member")
      List<Board> boardList = new ArrayList<>();

      @OneToMany(mappedBy = "member")
      List<Product> productList = new ArrayList<>();


      public MemberSameDto toSameDTO(){
            return new MemberSameDto(this.membername, this.memberNumber, this.password,
                    this.phoneNumber, this.account);
      }

}
