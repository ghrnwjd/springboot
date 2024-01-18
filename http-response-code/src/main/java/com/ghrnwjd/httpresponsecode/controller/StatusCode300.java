package com.ghrnwjd.httpresponsecode.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/status")
public class StatusCode300 {
    @GetMapping("/301")
    public String movePermanently(HttpServletResponse response) {
        response.setStatus(301);
        return "/notUsedPage.html";
    }
}
