package com.example.iamport.data.dto.same;

import com.example.iamport.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSameDto {
      private String title;
      private String content;
}
