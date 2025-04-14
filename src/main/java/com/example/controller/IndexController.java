package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}

