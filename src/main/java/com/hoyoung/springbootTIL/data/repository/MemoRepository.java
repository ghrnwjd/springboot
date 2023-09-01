package com.hoyoung.springbootTIL.data.repository;

import com.hoyoung.springbootTIL.data.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Integer> {

}
