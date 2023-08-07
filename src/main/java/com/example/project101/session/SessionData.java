package com.example.project101.session;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Data
public class SessionData {
    private String memberEmail;
    private String memberName;

    public static void setLoggedIn(HttpServletRequest request, String email, String name) {
        HttpSession session = request.getSession();
        session.setAttribute("memberEmail", email);
        session.setAttribute("memberName", name);
    }

    public static void setLoggedOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("memberEmail");
        session.removeAttribute("memberName");
        session.invalidate();
    }

    public SessionData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        setMemberEmail((String) session.getAttribute("memberEmail"));
        setMemberName((String) session.getAttribute("memberName"));
    }
}