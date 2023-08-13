package com.example.project101.controller;

import com.example.project101.dto.MemberTableDTO;
import com.example.project101.session.SessionData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private final SessionData sessionData;

    public MainController(SessionData sessionData) {
        this.sessionData = sessionData;
    }

    @GetMapping("/")
    public String showMainPage() {
        System.out.println("MainController.showMainPage");
        return "main"; // main.html의 파일명
    }

    @GetMapping("/page")
    public String dynamicPage(@RequestParam("page") String page, HttpSession session, Model model) {
        switch (page) {
            case "main":
                return "main"; // main.html의 파일명
            case "intro":
                return "intro"; // intro.html의 파일명
            case "mypage":
                MemberTableDTO loggedInUser = sessionData.getLoggedInUser(session);
                if (loggedInUser != null) {
                    model.addAttribute("loggedInUser", loggedInUser);
                    return "mypage"; // mypage.html의 파일명
                } else {
                    return "redirect:/login"; // 로그인되지 않았다면 로그인 페이지로 이동
                }
            case "upload":
                return "upload"; // upload.html의 파일명
            case "login":
                return "login";
            case "register":
                return "register";
            default:
                return "main"; // 기본적으로 main.html로 이동
        }
    }
}