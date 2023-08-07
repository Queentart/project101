package com.example.project101.session;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Data
public class SessionData {
    private String memberName;
    private String memberPassword;
    private Long memberId;

    public static void setLoggedIn(HttpServletRequest request, String name, String password, Long memberId) {
        HttpSession session = request.getSession();
        session.setAttribute("memberName", name);
        session.setAttribute("memberPassword", password);
        session.setAttribute("memberId", memberId);
    }

    public static void setLoggedOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("memberName");
        session.removeAttribute("memberPassword");
        session.removeAttribute("memberId");
        session.invalidate();
    }

    public SessionData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        setMemberName((String) session.getAttribute("memberName"));
        setMemberPassword((String) session.getAttribute("memberPassword"));
        setMemberId((Long) session.getAttribute("memberId"));
    }

    public Long getMemberId() {
        return memberId;
    }
}