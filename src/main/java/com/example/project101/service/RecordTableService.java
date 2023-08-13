package com.example.project101.service;

import com.example.project101.entity.MemberTable;
import com.example.project101.entity.RecordTable;
import com.example.project101.repository.RecordTableRepository;
import com.example.project101.repository.MemberTableRepository; // import 추가
import com.example.project101.session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RecordTableService {

    @Autowired
    private RecordTableRepository recordTableRepository;

    @Autowired
    private MemberTableRepository memberTableRepository; // import 추가

    public List<RecordTable> getRecordsByLoggedInUser(HttpServletRequest request) {
        // 로그인한 사용자 정보를 가져옴
        SessionData sessionData = new SessionData(request);
        String loggedInUserEmail = sessionData.getMemberEmail();

        // 로그인한 사용자의 기록들을 가져옴
        MemberTable loggedInUser = memberTableRepository.findByEmail(loggedInUserEmail);
        return recordTableRepository.findByMember(loggedInUser);
    }

    // 추가적인 비즈니스 로직 메소드가 필요한 경우 여기에 작성
}