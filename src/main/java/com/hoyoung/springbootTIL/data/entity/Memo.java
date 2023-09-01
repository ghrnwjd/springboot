package com.hoyoung.springbootTIL.data.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Memo {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int boardId;

      @Lob
      private String userIntro;

      @Builder
      public Memo(String userIntro) {
            this.userIntro = userIntro;
      }
}
