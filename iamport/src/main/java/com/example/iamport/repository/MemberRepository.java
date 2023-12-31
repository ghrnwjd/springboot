package com.example.iamport.repository;



import com.example.iamport.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
      Member findByMembername(String membername);
      Member findByMemberNumber(String memberNumber);
      Member findByMemberNumberAndPassword(String membernumber, String password);
}

