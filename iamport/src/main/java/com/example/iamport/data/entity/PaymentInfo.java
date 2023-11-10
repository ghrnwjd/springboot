package com.example.iamport.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Payment")
public class PaymentInfo {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "PAYMENT_ID")
      private int paymentId;

      @ManyToOne
      @JoinColumn(name = "MEMBER_ID")
      private Member member;

      @Column(nullable = false, length = 100)
      private String payMethod;

      @Column(nullable = false, length = 100)
      private String impUid;

      @Column(nullable = false, length = 100)
      private String merchantUid;

      @OneToOne
      @JoinColumn(name = "PRODUCT_ID")
      private Product product;

}
