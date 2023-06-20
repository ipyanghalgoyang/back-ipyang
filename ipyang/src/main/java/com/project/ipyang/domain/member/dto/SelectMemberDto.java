package com.project.ipyang.domain.member.dto;


import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.entity.MemberRoleType;
import lombok.*;

@Setter
@Getter
@ToString(exclude = "passwd")
@RequiredArgsConstructor
public class SelectMemberDto {
    private String email;
    private String nickname;
    private String passwd;
    private String name;
    private String phone;
    private MemberRoleType memberRoleType;
    private String address;
    private String withdraw;
    private int point;

    public SelectMemberDto (Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.passwd = member.getPasswd();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.memberRoleType = member.getMember_role();
        this.address = member.getAddress();
        this.withdraw = member.getWithdraw();
        this.point = member.getPoint();
    }

}
