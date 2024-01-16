package com.ghrnwjd.httpresponsecode;


import com.ghrnwjd.httpresponsecode.data.entity.Person;
import com.ghrnwjd.httpresponsecode.data.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Slf4j
@Configuration
public class DBinit {

    @Bean
    CommandLineRunner init(PersonRepository personRepository) {

        return args -> {
            // save a few customers
            personRepository.saveAll(Arrays.asList(new Person("Jack", "Bauer"),
                            new Person("Chloe", "O'Brian"),
                            new Person("Kim", "Bauer"),
                            new Person("David", "Palmer"),
                            new Person("Michelle", "Dessler")));


            for(Person person : personRepository.findAll()) {
                log.info(person.toString());
            }
        };
    }
}
