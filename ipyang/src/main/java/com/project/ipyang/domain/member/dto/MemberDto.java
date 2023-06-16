package com.project.ipyang.domain.member.dto;

import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.inquire.entity.Inquire;
import com.project.ipyang.domain.notice.entity.Notice;
import com.project.ipyang.domain.point.entity.Point;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.warning.entity.Warning;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String nickname;
    private String passwd;
    private String name;
    private String phone;
    private String common_role;
    private String address;
    private String point;
    private String img_context;
    private String original_file;
    private String img_stored_file;
    private List<Point> points = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Board> boards = new ArrayList<>();
    private List<Inquire> inquires = new ArrayList<>();
    private List<Notice> notices = new ArrayList<>();
    private List<Warning> warnings = new ArrayList<>();


    public MemberDto(Long id, String email, String nickname, String passwd, String name,
                     String phone, String common_role, String address, String point,
                     String img_context, String original_file, String img_stored_file,
                     List<Point> points, List<Product> products, List<Board> boards,
                     List<Inquire> inquires, List<Notice> notices, List<Warning> warnings) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.passwd = passwd;
        this.name = name;
        this.phone = phone;
        this.common_role = common_role;
        this.address = address;
        this.point = point;
        this.img_context = img_context;
        this.original_file = original_file;
        this.img_stored_file = img_stored_file;
        this.points = points;
        this.products = products;
        this.boards = boards;
        this.inquires = inquires;
        this.notices = notices;
        this.warnings = warnings;
    }

    public MemberDto(Long id, String email, String nickname, String passwd, String name, String phone, String commonRole, String address, String point, String imgContext, String originalFile, String imgStoredFile) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.passwd = passwd;
        this.name = name;
        this.phone = phone;
        this.common_role = common_role;
        this.address = address;
        this.point = point;
        this.img_context = img_context;
        this.original_file = original_file;
        this.img_stored_file = img_stored_file;
    }
}
