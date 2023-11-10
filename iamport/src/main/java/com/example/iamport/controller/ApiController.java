package com.example.iamport.controller;


import com.example.iamport.data.dto.BoardDto;
import com.example.iamport.data.dto.MemberJoinDto;
import com.example.iamport.data.dto.MemberLoginDto;
import com.example.iamport.data.dto.ProductBuyDto;
import com.example.iamport.data.dto.same.MemberSameDto;
import com.example.iamport.data.entity.Member;
import com.example.iamport.service.BoardService;
import com.example.iamport.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {


      private BoardService boardService;
      private MemberService memberService;


      public ApiController(BoardService boardService, MemberService memberService) {
            this.boardService = boardService;
            this.memberService = memberService;
      }


      @PostMapping("/board/new")
      public void 글쓰기(@RequestBody BoardDto boardDto) {
            boardService.글쓰기(boardDto);
      }


      @PutMapping("/board/buy")
      public void 상품구매(@RequestBody ProductBuyDto productBuyDto) {
            System.out.println(productBuyDto);
            boardService.상품구매(productBuyDto);
      }

      @PostMapping("/member/new")
      public String 멤버추가(@RequestBody MemberJoinDto memberJoinDTO) {
            memberService.멤버추가(memberJoinDTO);
            return  "멤버추가됨";
      }


}
