package com.hoyoung.springbootTIL.data.dto;


import com.hoyoung.springbootTIL.data.entity.Memo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemoDto {

      private String userIntro;

      public Memo toEntity() {
            return Memo.builder().userIntro(userIntro).build();
      }
}
