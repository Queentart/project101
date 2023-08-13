package com.example.project101.controller;

import com.example.project101.dto.MemberTableDTO;
import com.example.project101.service.MemberTableService;
import com.example.project101.session.SessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberTableService memberService;
    private final SessionData sessionData;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("memberDTO", new MemberTableDTO()); // 빈 MemberTableDTO를 모델에 추가
        return "login"; // 로그인 페이지를 보여줌
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberTableDTO memberDTO, HttpSession session, Model model) {
        MemberTableDTO loginResult = memberService.login(memberDTO.getEmail(), memberDTO.getPassword());
        if (loginResult != null) {
            sessionData.setLoggedInUser(loginResult, session);
            return "redirect:/mypage"; // 로그인 성공 시 마이페이지로 이동
        } else {
            model.addAttribute("errorMessage", "로그인 실패: 이메일 또는 비밀번호가 잘못되었습니다."); // 에러 메시지 전달
            return "login"; // 로그인 실패 시 다시 로그인 페이지로 이동
        }
    }
}