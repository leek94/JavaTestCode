package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class DemoController {

    @GetMapping("/")
    public String get(){
        return "Hello World!";
    }

    @PostMapping("/")
    public String post(){
        return "Hello Post World!";
    }
}
