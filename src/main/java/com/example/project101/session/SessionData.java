package com.example.project101.session;

import com.example.project101.dto.MemberTableDTO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionData {

    private static final String LOGGED_IN_USER_ATTRIBUTE = "loggedInUser";

    public void setLoggedInUser(MemberTableDTO loggedInUser, HttpSession session) {
        int sessionTimeoutInSeconds = 3600;
        session.setMaxInactiveInterval(sessionTimeoutInSeconds);

        session.setAttribute(LOGGED_IN_USER_ATTRIBUTE, loggedInUser);
    }

    public void updateLoggedInUser(MemberTableDTO loggedInUser, HttpSession session) {
        MemberTableDTO existingUser = getLoggedInUser(session);
        if (existingUser != null) {
            existingUser.setName(loggedInUser.getName());
            // You can update other fields as well if needed
            session.setAttribute(LOGGED_IN_USER_ATTRIBUTE, existingUser);
        }
    }

    public MemberTableDTO getLoggedInUser(HttpSession session) {
        return (MemberTableDTO) session.getAttribute(LOGGED_IN_USER_ATTRIBUTE);
    }

    public void removeLoggedInUser(HttpSession session) {
        session.removeAttribute(LOGGED_IN_USER_ATTRIBUTE);
    }
}