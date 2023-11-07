package com.hoyoung.springbootTIL.service;
import com.hoyoung.springbootTIL.data.dto.MemoDto;
import com.hoyoung.springbootTIL.data.entity.Memo;
import com.hoyoung.springbootTIL.data.entity.User;
import com.hoyoung.springbootTIL.data.repository.MemoRepository;
import com.hoyoung.springbootTIL.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {

      private MemoRepository memoRepository;
      private UserRepository userRepository;

      public MemoService(MemoRepository memoRepository, UserRepository userRepository) {
            this.memoRepository = memoRepository;
            this.userRepository = userRepository;
      }

      @Transactional
      public Memo saveBoard(String userName, MemoDto memoDto) {
            User user = userRepository.findByUserName(userName);
            Memo memo = memoDto.toEntity();
            memoRepository.save(memo);
            user.getMemoList().add(memo);

            return memo;
      }

      public List<Memo> searchUserBoard(String userName) {
            return userRepository.findByUserName(userName).getMemoList();
      }
}
