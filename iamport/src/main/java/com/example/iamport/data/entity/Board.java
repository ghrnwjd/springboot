package com.example.iamport.data.entity;


import com.example.iamport.data.dto.same.BoardSameDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "BOARD_ID")
      private int id;

      @Column(nullable = false)
      private String title;

      @Lob
      private String content;

      @OneToOne
      @JoinColumn(name = "PRODUCT_ID")
      private Product product;

      @ManyToOne
      @JoinColumn(name = "MEMBER_ID")
      private Member member;

      @Builder
      public Board(String title, String content,Product product, Member member) {
            this.title = title;
            this.content = content;
            this.product = product;
            this.member = member;
      }

      public BoardSameDto toSameDTO() {
            return new BoardSameDto(this.title, this.content);
      }
}
