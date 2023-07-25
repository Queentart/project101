package com.example.project101.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    @RequestMapping("/")
    String home() {
        return "정상 작동중 입니다.";
    }
}
