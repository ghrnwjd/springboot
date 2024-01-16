package com.ghrnwjd.httpresponsecode.data.repository;


import com.ghrnwjd.httpresponsecode.data.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
