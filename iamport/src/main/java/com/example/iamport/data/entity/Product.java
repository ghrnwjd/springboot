package com.example.iamport.data.entity;

import com.example.iamport.data.dto.same.ProductSameDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "PRODUCT_ID")
      private int productId;

      @Column(nullable = false)
      private String productName;

      @Column(nullable = false)
      private int productPrice;

      @ManyToOne
      @JoinColumn(name = "MEMBER_ID")
      private Member member;

      @Builder
      public Product(int productId, String productName, int productPrice, Member member) {
            this.productId = productId;
            this.productName = productName;
            this.productPrice = productPrice;
            this.member = member;
      }

      public ProductSameDto toSameDTO() {
            return new ProductSameDto(this.productName, this.productPrice);
      }
}
