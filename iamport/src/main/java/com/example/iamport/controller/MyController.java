package com.example.iamport.controller;

import com.example.iamport.data.dto.BoardResDto;
import com.example.iamport.data.entity.Board;
import com.example.iamport.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MyController {

      private BoardService boardService;
      public MyController(BoardService boardService) {
            this.boardService = boardService;
      }

      @GetMapping({"/", ""})
      public String 인덱스() {
            return "index";
      }

      @GetMapping("/board")
      public String 게시판(Model model) {
            List<BoardResDto> boardResDtoList = boardService.전체글보기();

            model.addAttribute("boards", boardResDtoList);
            return "board";
      }

      @GetMapping("/board/view/{id}")
      public String 게시판글보기(@PathVariable("id") String boardId, Model model) {
            Board board = boardService.글보기(boardId).orElseGet( () -> {
                  return null;
            });

             if(board != null) {

                   model.addAttribute("board", board.toSameDTO());
                   model.addAttribute("member", board.getMember().toSameDTO());
                   model.addAttribute("product", board.getProduct().toSameDTO());

             }
             return "boardView";
      }
}


