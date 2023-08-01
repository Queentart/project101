package com.example.project101.session;

import lombok.Data;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

@Data
public class SessionData {
    private String memberEmail;
    private String memberName;

    public SessionData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        setMemberEmail((String) session.getAttribute("memberEmail"));
        setMemberName((String) session.getAttribute("memberName"));

    }
}
