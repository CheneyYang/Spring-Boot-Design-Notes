package com.github.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class User {

    @RequestMapping("/getUser")
    public String getUser(){
        return "getUser";
    }
}
