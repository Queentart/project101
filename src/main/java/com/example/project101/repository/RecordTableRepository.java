package com.example.project101.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.project101.entity.MemberTable;
import com.example.project101.entity.RecordTable;
import java.util.List;

@Repository
public interface RecordTableRepository extends JpaRepository<RecordTable, Long> {

    // 로그인한 사용자가 생성한 기록을 조회하는 메소드
    List<RecordTable> findByMember(MemberTable member);
}