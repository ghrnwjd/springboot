package com.ghrnwjd.httpresponsecode;


import com.ghrnwjd.httpresponsecode.data.RoleType;
import com.ghrnwjd.httpresponsecode.data.entity.Person;
import com.ghrnwjd.httpresponsecode.data.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;

import java.util.Arrays;


@Slf4j
@Configuration
public class DBinit {

    @Bean
    CommandLineRunner init(PersonRepository personRepository) {

        return args -> {
            // save a few customers
            personRepository.saveAll(Arrays.asList(
                        new Person("Jung", "1234", RoleType.ADMIN),
                        new Person("Jack", "1", RoleType.USER),
                        new Person("Chloe", "2", RoleType.USER),
                        new Person("Kim", "3", RoleType.USER),
                        new Person("David", "4", RoleType.USER),
                        new Person("Michelle", "5", RoleType.USER)));


            for(Person person : personRepository.findAll()) {
                log.info(person.toString());
            }
        };
    }
}
