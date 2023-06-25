package com.project.ipyang.domain.adopt.entity;

import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.adopt.dto.AdoptImgDto;
import com.project.ipyang.domain.adopt.dto.SelectAdoptDto;
import com.project.ipyang.domain.adopt.dto.WriteAdoptDto;
import com.project.ipyang.domain.apply.entity.Apply;
import com.project.ipyang.domain.catType.entity.CatType;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.vaccine.entity.Vaccine;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Adopt extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adopt_id")
    private Long id;

    @Column(name = "a_title")
    private String title;

    @Column(name = "a_content")
    private String content;

    @Column(name = "a_view_cnt")
    private int view;

    @Column(name = "a_name")
    private String name;

    @Column(name = "a_gender")
    private String gender;

    @Column(name = "a_weight")
    private String weight;

    @Column(name = "a_age")
    private String age;

    @Column(name = "a_neu")
    private String neu;

    @Column(name = "a_adopted_yn")
    private int yn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    private CatType catType;

    @OneToMany(mappedBy = "adopt")
    @OrderColumn(name = "adopt_img_order")
    private List<AdoptImg> adoptImgs = new ArrayList<>();

    @OneToMany(mappedBy = "adopt")
    private List<Apply> applies = new ArrayList<>();

    public WriteAdoptDto convertWriteDto() {
        return new WriteAdoptDto().builder()
                                            .title(title)
                                            .content(content)
                                            .view(0)
                                            .name(name)
                                            .gender(gender)
                                            .weight(weight)
                                            .age(age)
                                            .neu(neu)
                                            .yn(0)
                                            .vaccineId(vaccine.getId())
                                            .catId(catType.getId())
                                            .build();
    }

    public SelectAdoptDto convertDto() {
        return new SelectAdoptDto().builder()
                                            .id(id)
                                            .title(title)
                                            .content(content)
                                            .view(view)
                                            .name(name)
                                            .gender(gender)
                                            .weight(weight)
                                            .gender(gender)
                                            .age(age)
                                            .neu(neu)
                                            .yn(yn)
                                            .adoptImgs(convertImgDto(adoptImgs))
                                            .memberId(member.getId())
                                            .vacId(vaccine.getId())
                                            .catId(catType.getId())
                                            .build();
    }



    public List<AdoptImgDto> convertImgDto(List<AdoptImg> adoptImgs) {
        if(adoptImgs.isEmpty()) return null;

        List<AdoptImgDto> adoptImgDtoList = new ArrayList<>();
        AdoptImgDto adoptImgDto = null;

        for(AdoptImg adoptImg : adoptImgs) {
            adoptImgDtoList.add(adoptImgDto.builder()
                                                    .id(adoptImg.getId())
                                                    .imgOriginFile(adoptImg.getImgOriginFile())
                                                    .imgStoredFile(adoptImg.getImgStoredFile())
                                                    .build());
        }
        return adoptImgDtoList;
    }


    public void update(String title, String content, String name, String gender, String weight,
                       String age, String neu, int yn, Vaccine vaccine, CatType catType) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.age = age;
        this.neu = neu;
        this.yn = yn;
        this.vaccine = vaccine;
        this.catType = catType;
    }
}
