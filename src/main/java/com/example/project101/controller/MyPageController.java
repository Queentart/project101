package com.example.project101.controller;

import com.example.project101.entity.RecordTable;
import com.example.project101.service.RecordTableService;
import com.example.project101.session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    private RecordTableService recordTableService;

    @GetMapping("/mypage")
    public String showMyPage(HttpServletRequest request, Model model) {
        List<RecordTable> records = recordTableService.getRecordsByLoggedInUser(request);
        model.addAttribute("records", records);
        return "mypage";
    }

    // 기타 다른 마이 페이지와 관련된 컨트롤러 메소드들
    // ...
}