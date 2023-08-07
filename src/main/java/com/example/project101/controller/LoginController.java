package com.example.project101.controller;

import com.example.project101.entity.MemberTable;
import com.example.project101.entity.RecordTable;
import com.example.project101.repository.MemberTableRepository;
import com.example.project101.repository.RecordTableRepository;
import com.example.project101.session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private MemberTableRepository memberTableRepository;

    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request) {
        SessionData sessionData = new SessionData(request);
        if (sessionData.getMemberEmail() != null) {
            // 이미 로그인된 상태라면 마이페이지로 리다이렉트
            return "redirect:/mypage";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {
        String storedPassword = memberTableRepository.findPasswordByEmail(email);
        if (storedPassword != null && storedPassword.equals(password)) {
            String name = memberTableRepository.findNameByEmail(email);
            SessionData.setLoggedIn(request, email, name);
            return "redirect:/mypage"; // 로그인 성공 시 마이페이지로 이동
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SessionData.setLoggedOut(request);
        return "redirect:/login"; // 로그아웃 시 로그인 페이지로 이동
    }
}