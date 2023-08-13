package com.example.project101.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @GetMapping
    public String showMyPage() {
        return "mypage"; // mypage.html의 파일명
    }

    @GetMapping("/record")
    public String showRecordsPage() {
        return "record"; // record.html로 이동
    }
}





