package com.example.project101.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String showMainPage() {
        System.out.println("MainController.showMainPage");
        return "main"; // main.html의 파일명
    }

    @GetMapping("/page")
    public String dynamicPage(@RequestParam("page") String page) {
        switch (page) {
            case "main":
                return "main"; // main.html의 파일명
            case "intro":
                return "intro"; // intro.html의 파일명
            case "mypage":
                return "mypage"; // mypage.html의 파일명
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





