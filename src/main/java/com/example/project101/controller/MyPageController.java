package com.example.project101.controller;

import com.example.project101.dto.MemberTableDTO;
import com.example.project101.session.SessionData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MyPageController {

    private final SessionData sessionData;

    public MyPageController(SessionData sessionData) {
        this.sessionData = sessionData;
    }

    @GetMapping("/mypage")
    public String showMypage(HttpSession session, Model model) {
        MemberTableDTO loggedInUser = sessionData.getLoggedInUser(session);
        if (loggedInUser != null) {
            model.addAttribute("username", loggedInUser.getName()); // 회원명을 모델에 추가
            return "mypage"; // 마이페이지를 보여줌
        } else {
            return "redirect:/login"; // 로그인되지 않았다면 로그인 페이지로 이동
        }
    }
}