package com.ghrnwjd.httpresponsecode.controller;

import com.ghrnwjd.httpresponsecode.data.dto.PersonDTO;
import com.ghrnwjd.httpresponsecode.data.dto.ResponseDTO;
import com.ghrnwjd.httpresponsecode.data.entity.Person;
import com.ghrnwjd.httpresponsecode.service.PersonService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/status")
public class StatusCode200 {

    @Autowired
    private PersonService personService;
    @GetMapping("/200")
    public ResponseDTO success() {
        return ResponseDTO.builder()
                .code(HttpStatus.OK.value())
                .response(HttpStatus.OK)
                .comment("요청 성공.")
                .build();
    }

    @PostMapping("/201")
    public ResponseDTO<Person> created(@RequestBody PersonDTO dto, HttpServletResponse response) {

        response.setStatus(201);
        Person person = personService.save(dto);
        log.info(person.toString());

        return new ResponseDTO<Person>(person, HttpStatus.ACCEPTED.value(),
                HttpStatus.ACCEPTED, "요청이 정상적이며 새로운 리소스가 생겼습니다.");
    }

    @PostMapping("/204")
    public ResponseDTO noContent(@RequestBody PersonDTO dto, HttpServletResponse response) {

        response.setStatus(204);
        log.info(dto.toString());
        return ResponseDTO.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .response(HttpStatus.NO_CONTENT)
                .comment("요청은 정상적이나 응답할 데이터가 없습니다.")
                .build();
    }


}
