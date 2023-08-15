package com.example.project101.dto;

import com.example.project101.entity.MemberTable;
import lombok.*;

import java.lang.reflect.Member;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberTableDTO {

    private Long Id;

    private String Email;

    private String Password;

    private String Name;

    public static MemberTableDTO toMemberDTO(MemberTable memberTable) {
        MemberTableDTO memberDTO = new MemberTableDTO();
        memberDTO.setId(memberTable.getId());
        memberDTO.setEmail(memberTable.getEmail());
        memberDTO.setPassword(memberTable.getPassword());
        memberDTO.setName(memberTable.getName());
        return memberDTO;

    }
}
