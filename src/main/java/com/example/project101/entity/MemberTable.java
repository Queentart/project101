package com.example.project101.entity;

import com.example.project101.dto.MemberTableDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Member")
public class MemberTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    // Getters and Setters

    // Constructors, toString, etc.

    public static MemberTable toMemberTable(MemberTableDTO memberDTO) {
        MemberTable memberTable = new MemberTable();
        memberTable.setEmail(memberDTO.getEmail());
        memberTable.setPassword(memberDTO.getPassword());
        memberTable.setName(memberDTO.getName());
        return memberTable;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
