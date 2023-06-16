package com.project.ipyang.domain.member.dto;

import lombok.Data;

@Data
public class SelectMemberDto {
    private String email;
    private String nickname;
    private String passwd;
    private String name;
    private String phone;
    private String common_role;
    private String address;
    private int    point;
}
