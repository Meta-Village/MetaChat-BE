package com.ohgiraffers.metachatbe.user.controller;


import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class TestController {
    @Hidden
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @Hidden
    @PostMapping("/test")
    public String test2(){
        return "test";
    }
}
