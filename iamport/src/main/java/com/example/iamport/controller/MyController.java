package com.example.iamport.controller;

import com.example.iamport.data.dto.BoardResDto;
import com.example.iamport.data.dto.MemberLoginDto;
import com.example.iamport.data.dto.same.MemberSameDto;
import com.example.iamport.data.entity.Board;
import com.example.iamport.service.BoardService;
import com.example.iamport.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class MyController {

      private BoardService boardService;
      private MemberService memberService;
      public MyController(BoardService boardService, MemberService memberService) {
            this.boardService = boardService;
            this.memberService = memberService;
      }

      @GetMapping({"/", ""})
      public String 인덱스(HttpServletRequest request) {
            System.out.println("Logined memberNumber is : " + request.getSession().getAttribute("memberNumber"));
            return "index";
      }

      @GetMapping("/board")
      public String 게시판(Model model) {
            List<BoardResDto> boardResDtoList = boardService.전체글보기();

            model.addAttribute("boards", boardResDtoList);
            return "board";
      }



      @GetMapping("/board/view/{id}")
      public String 게시판글보기(@PathVariable("id") String boardId, Model model, HttpServletRequest request) {
            Board board = boardService.글보기(boardId).orElseGet( () -> {
                  return null;
            });

             if(board != null) {
                   model.addAttribute("board", board.toSameDTO());
                   model.addAttribute("member", board.getMember().toSameDTO());
                   model.addAttribute("product", board.getProduct().toSameDTO());
                   model.addAttribute("buyer", request.getSession().getAttribute("memberNumber"));
             }
             return "boardView";
      }

      @GetMapping("/login")
      public String login(){
            return "login";
      }

      @PostMapping("/login")
      public String 로그인(@RequestBody MemberLoginDto memberLoginDto, HttpServletRequest request) {
            MemberSameDto member = memberService.로그인(memberLoginDto);

            if(member != null) {
                  HttpSession session = request.getSession();
                  session.setAttribute("memberNumber", memberLoginDto.getMemberNumber());
                  session.setMaxInactiveInterval(60 * 30);
            }

            return "index";
      }

      @GetMapping("/logout")
      public String 로그아웃(HttpServletRequest request) {
            request.getSession().invalidate();
            return "index";
      }
}


