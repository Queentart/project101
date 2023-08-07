package com.example.project101.repository;

import com.example.project101.entity.MemberTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;

@Repository
public interface MemberTableRepository extends JpaRepository<MemberTable, Long> {
    // 추가적인 메소드가 필요한 경우 여기에 작성
    // 예: MemberTable findByEmail(String email);

    MemberTable findByEmail(String email);

    @Query("SELECT password FROM MemberTable WHERE email = :email")
    String findPasswordByEmail(@Param("email") String email);

    @Query("SELECT email FROM MemberTable WHERE email = :email")
    String findEmailByEmail(@Param("email") String email);

    @Query("SELECT name FROM MemberTable WHERE email = :email")
    String findNameByEmail(@Param("email") String email);

    default String getHttpServletRequest(HttpSession session) {
        if (session != null) {
            return (String) session.getAttribute("memberEmail");
        }
        return null;
    }
}