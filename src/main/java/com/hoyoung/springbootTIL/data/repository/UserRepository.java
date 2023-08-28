package com.hoyoung.springbootTIL.data.repository;


import com.hoyoung.springbootTIL.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
      User findByUserName(String userName);

}
