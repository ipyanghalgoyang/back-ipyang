package com.project.ipyang.domain.member.entity;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.*;
import com.project.ipyang.domain.adopt.entity.Adopt;
import com.project.ipyang.domain.apply.entity.Apply;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.inquire.entity.Inquire;
import com.project.ipyang.domain.member.dto.DeleteMemberDto;
import com.project.ipyang.domain.member.dto.MemberDto;
import com.project.ipyang.domain.notice.entity.Notice;
import com.project.ipyang.domain.point.entity.Point;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.warning.entity.Warning;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "m_email")
    private String email;

    @Column(name = "m_nickname")
    private String nickname;

    @Column(name = "m_passwd")
    private String passwd;

    @Column(name = "m_name")
    private String name;

    @Column(name = "m_phone")
    private String phone;

    @Column(name = "m_common_role")
    @Enumerated(EnumType.STRING)
    private IpyangEnum.MemberRoleType memberRole;

    @Column(name = "m_address")
    private String address;

    @Column(name = "m_point")
    private int point;

    @Column(name = "m_delyn")
    @Enumerated(EnumType.STRING)
    private IpyangEnum.MemberDelYN delYn;  //회원탈퇴여부  y면 y일때 30일뒤에 삭제 n이면 현상태유지

    @Column(name = "m_img_context")
    private String imgContext;

    @Column(name = "m_original_file")
    private String imgOriginFile;

    @Column(name = "m_img_stored_file")
    private String imgStoredFile;

    @OneToMany(mappedBy = "member")
    private List<Point> points = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Product> products = new ArrayList<>();

     @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

     @OneToMany(mappedBy = "member")
    private List<Inquire> inquires = new ArrayList<>();

     @OneToMany(mappedBy = "member")
     @OrderColumn(name = "notice_order")
    private List<Notice> notices = new ArrayList<>();

     @OneToMany(mappedBy = "member")
     @OrderColumn(name = "warning_order")
    private List<Warning> warnings = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @OrderColumn(name = "adopt_order")
    private List<Adopt> adopts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Apply> applies = new ArrayList<>();

    public MemberDto convertDto(){
        return MemberDto.builder()
                                .id(id)
                                .email(email)
                                .nickname(nickname)
                                .passwd(passwd)
                                .name(name)
                                .phone(phone)
                                .memberRole(memberRole)
                                .address(address)
                                .point(point)
                                .build();
    }

    public DeleteMemberDto convertDelDto() {
        return DeleteMemberDto.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .passwd(passwd)
                .name(name)
                .phone(phone)
                .memberRole(memberRole)
                .address(address)
                .point(point)
                .delYn(delYn)
                .build();
    }

    public void setDelYn(IpyangEnum.MemberDelYN delYn) {
        this.delYn = delYn;
    }



}

