package com.example.project101.repository;

import com.example.project101.entity.MemberTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberTableRepository extends JpaRepository<MemberTable, Long> {
    // 이메일로 회원 정보 조회(select * from member_table where member_email=?)

    Optional<MemberTable> findByEmail(String Email);
}
