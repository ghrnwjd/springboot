package com.example.iamport.service;

import com.example.iamport.data.dto.BoardDto;
import com.example.iamport.data.dto.BoardResDto;
import com.example.iamport.data.dto.ProductBuyDto;
import com.example.iamport.data.entity.Board;
import com.example.iamport.data.entity.Member;
import com.example.iamport.data.entity.Product;
import com.example.iamport.repository.BoardRepository;
import com.example.iamport.repository.MemberRepository;
import com.example.iamport.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BoardService {

      private final BoardRepository boardRepository;
      private final MemberRepository memberRepository;
      private final ProductRepository productRepository;
      private final MemberService memberService;


      public BoardService(BoardRepository boardRepository, MemberRepository memberRepository, ProductRepository productRepository, MemberService memberService) {
            this.boardRepository = boardRepository;
            this.memberRepository = memberRepository;
            this.productRepository = productRepository;
            this.memberService = memberService;
      }

      @Transactional
      public void 글쓰기(BoardDto boardDto) {
            Member member = memberService.학회원찾기(boardDto.getUsername());

            Product product = Product
                    .builder()
                    .productName(boardDto.getProductName())
                    .productPrice(boardDto.getPrice())
                    .member(member)
                    .build();

            Board board = Board.builder()
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .product(product)
                    .member(member)
                    .build();

            boardRepository.save(board);
            productRepository.save(product);
      }

      public List<BoardResDto> 전체글보기() {

            List<Board> boards = boardRepository.findAll();
            List<BoardResDto> boardResDtos = new ArrayList<>();

            for(Board board : boards) {
                  boardResDtos.add(BoardResDto.builder()
                          .id(board.getId())
                          .title(board.getTitle())
                          .name(board.getMember().getMembername())
                          .build());
            }
            return boardResDtos;
      }

      public Optional<Board> 글보기(String boardId) {
            return boardRepository.findById(Integer.parseInt(boardId));
      }


      @Transactional
      public void 상품구매(ProductBuyDto productBuyDto) {
            String cellerName = productBuyDto.getCellerName();
            String consumerNumber = productBuyDto.getBuyerNumber();
            int productPrice = Integer.parseInt(productBuyDto.getProductPrice());

            Product product = productRepository.findByProductName(productBuyDto.getProductName());
            Member celler = memberRepository.findByMembername(cellerName);
            Member consumer = memberRepository.findByMemberNumber(consumerNumber);
            Board board = boardRepository.findById(productBuyDto.getBoardId()).orElseGet(() -> {
                  return null;
            });

            System.out.println("구매 전 판매자 잔고 : " + celler.getAccount());
            System.out.println("구매 전 구매자 잔고 : " + consumer.getAccount());
            celler.setAccount(celler.getAccount() + productPrice);
            consumer.setAccount(consumer.getAccount() - productPrice);

            System.out.println("구매 후 판매자 잔고 : " + celler.getAccount());
            System.out.println("구매 후 구매자 잔고 : " + consumer.getAccount());

            board.setMember(consumer);
            product.setMember(consumer);

      }

}
