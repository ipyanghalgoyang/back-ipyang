package com.project.ipyang.domain.adopt.entity;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "adopt_img")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AdoptImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopt_id")
    private Adopt adopt;

    @Column(name = "img_original_file")
    private String imgOriginFile;

    @Column(name = "img_stored_file")
    private String imgStoredFile;

    public static AdoptImg convertImgEntity(Adopt adopt, String imgOriginFile, String imgStoredFile) {
        AdoptImg adoptImg = AdoptImg.builder()
                                            .imgOriginFile(imgOriginFile)
                                            .imgStoredFile(imgStoredFile)
                                            .adopt(adopt)
                                            .build();
        return adoptImg;
    }


}
