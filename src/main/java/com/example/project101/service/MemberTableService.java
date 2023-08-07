package com.example.project101.service;

import com.example.project101.entity.MemberTable;
import com.example.project101.repository.MemberTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberTableService {

    @Autowired
    private MemberTableRepository memberTableRepository;

    public MemberTable findByEmail(String email) {
        return memberTableRepository.findByEmail(email);
    }

    // 추가적인 비즈니스 로직 메소드가 필요한 경우 여기에 작성
}