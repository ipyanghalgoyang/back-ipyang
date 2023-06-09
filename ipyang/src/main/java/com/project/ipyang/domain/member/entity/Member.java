package com.project.ipyang.domain.member.entity;

import com.project.ipyang.common.entity.*;
import com.project.ipyang.domain.adopt.entity.Adopt;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.inquire.entity.Inquire;
import com.project.ipyang.domain.notice.entity.Notice;
import com.project.ipyang.domain.point.entity.Point;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.warning.entity.Warning;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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
    private String common_role;

    @Column(name = "m_address")
    private String address;

    @Column(name = "m_point")
    private String point;

    @Column(name = "m_img_context")
    private String img_context;

    @Column(name = "m_original_file")
    private String original_file;

    @Column(name = "m_img_stored_file")
    private String img_stored_file;

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







}

