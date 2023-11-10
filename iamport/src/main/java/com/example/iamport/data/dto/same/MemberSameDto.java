package com.example.iamport.data.dto.same;

import com.example.iamport.data.entity.Board;
import com.example.iamport.data.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSameDto {
      private String membername;
      private String memberNumber;
      private String password;
      private String phoneNumber;
      private int account;
}
