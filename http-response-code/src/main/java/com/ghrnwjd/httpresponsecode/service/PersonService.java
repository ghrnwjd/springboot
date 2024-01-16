package com.ghrnwjd.httpresponsecode.service;

import com.ghrnwjd.httpresponsecode.data.dto.PersonDTO;
import com.ghrnwjd.httpresponsecode.data.entity.Person;
import com.ghrnwjd.httpresponsecode.data.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(PersonDTO personDTO) {

        Person person = personDTO.toEntity();
        personRepository.save(person);

        return person;
    }
}
