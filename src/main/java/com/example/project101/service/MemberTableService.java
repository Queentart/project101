package com.example.project101.service;

import com.example.project101.dto.MemberTableDTO;
import com.example.project101.entity.MemberTable;
import com.example.project101.repository.MemberTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberTableService {

    private final MemberTableRepository memberRepository;

    public boolean register(MemberTableDTO memberDTO) {
        if (!isEmailDuplicate(memberDTO.getEmail())) {
            MemberTable memberTable = MemberTable.toMemberTable(memberDTO);
            memberRepository.save(memberTable);
            return true; // 회원가입 성공
        }
        return false; // 회원가입 실패 (이미 중복된 이메일이 존재)
    }

    public MemberTableDTO login(String email, String password) {
        Optional<MemberTable> byEmail = memberRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            MemberTable memberTable = byEmail.get();
            if (memberTable.checkPassword(password)) {
                return MemberTableDTO.toMemberDTO(memberTable);
            }
        }
        return null; // 로그인 실패
    }

    private boolean isEmailDuplicate(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}