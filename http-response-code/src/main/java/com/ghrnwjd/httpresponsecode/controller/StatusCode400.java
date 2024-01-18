package com.ghrnwjd.httpresponsecode.controller;

import com.ghrnwjd.httpresponsecode.data.dto.PersonDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/status")
public class StatusCode400 {
    @PostMapping("/400")
    public String badRequest(@RequestBody PersonDTO personDTO) {
        return "/notUsedPage.html";
    }
    @GetMapping("/401")
    public String unAuthorized() {
        return "/notUsedPage.html";
    }
}
