package com.project.ipyang.domain.notice.dto;

import com.project.ipyang.common.IpyangEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class SelectNoticeDto {
    private Long id;
    private IpyangEnum.NoticeCategory category;
    private String title;
    private String content;
}
