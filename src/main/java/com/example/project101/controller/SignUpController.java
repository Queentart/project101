package com.example.project101.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignUpController {

    // 회원가입 페이지 출력 요청
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // register.html 파일명
    }

    @PostMapping("/register")
    public String save(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        System.out.println("SignUpController.save");
        System.out.println("name = " + name + ", email = " + email + ", password = " + password);
        return "redirect:/login"; // 회원가입 후 로그인 페이지로 이동
    }
}