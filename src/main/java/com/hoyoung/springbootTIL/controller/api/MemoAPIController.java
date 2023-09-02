package com.hoyoung.springbootTIL.controller.api;


import com.hoyoung.springbootTIL.data.dto.MemoDto;
import com.hoyoung.springbootTIL.data.dto.CommonResponseDto;
import com.hoyoung.springbootTIL.data.entity.Memo;
import com.hoyoung.springbootTIL.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class MemoAPIController {
      private MemoService memoService;

      public MemoAPIController(MemoService memoService) {
            this.memoService = memoService;
      }

      @PostMapping("/save/{userName}")
      public CommonResponseDto<Memo> saveBoard(@PathVariable("userName") String name, @RequestBody MemoDto memoDto) {
            return new CommonResponseDto<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                    memoService.saveBoard(name, memoDto));
      }

      @GetMapping("/{userName}")
      public CommonResponseDto<List<Memo>> saveBoard(@PathVariable("userName") String name) {
            return new CommonResponseDto<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                    memoService.searchUserBoard(name));
      }
}
