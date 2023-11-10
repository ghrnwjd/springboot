package com.example.iamport.service;


import com.example.iamport.data.dto.MemberJoinDto;
import com.example.iamport.data.dto.MemberLoginDto;
import com.example.iamport.data.dto.same.MemberSameDto;
import com.example.iamport.data.entity.Member;
import com.example.iamport.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

      private final MemberRepository memberRepository;

      public MemberService(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
      }


      public void 멤버추가(MemberJoinDto memberJoinDTO) {
            Member member = memberJoinDTO.toEntity();

            memberRepository.save(member);


      }
      public Member 학회원찾기(String membername) {
            return memberRepository.findByMembername(membername);
      }

      public MemberSameDto 로그인(MemberLoginDto memberLoginDto) {
            Member member = memberRepository.findByMemberNumberAndPassword(memberLoginDto.getMemberNumber(), memberLoginDto.getPassword());

            System.out.println(member.toSameDTO());
            return member.toSameDTO();

      }

}
