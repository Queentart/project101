package com.example.project101.controller;

import com.example.project101.dto.MemberTableDTO;
import com.example.project101.service.MemberTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final MemberTableService memberService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // 회원가입 페이지를 보여줌
    }

    @PostMapping("/register")
    public String register(@ModelAttribute MemberTableDTO memberDTO, Model model) {
        boolean registrationSuccess = memberService.register(memberDTO);
        if (registrationSuccess) {
            return "redirect:/login"; // 회원가입 후 로그인 페이지로 이동
        } else {
            model.addAttribute("errorMessage", "이미 사용 중인 이메일입니다."); // 에러 메시지 전달
            return "register"; // 회원가입 실패 시 다시 회원가입 페이지로 이동
        }
    }
}