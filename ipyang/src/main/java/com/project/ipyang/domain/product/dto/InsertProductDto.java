package com.project.ipyang.domain.product.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Data
public class InsertProductDto  {
    private String name;
    private IpyangEnum.ProductStatus status;
    private int price;
    private String type;
    private String loc;
    private Long memberId;
    private List<MultipartFile> imageFiles;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }


}
