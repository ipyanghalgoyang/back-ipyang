package com.project.ipyang.domain.catType.service;

import com.project.ipyang.domain.catType.dto.CatTypeDto;
import com.project.ipyang.domain.catType.dto.InsertCatTypeDto;
import com.project.ipyang.domain.catType.entity.CatType;
import com.project.ipyang.domain.catType.repository.CatTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatTypeService {

    private final CatTypeRepository catTypeRepository;

    public CatTypeDto createCatType(InsertCatTypeDto catTypeDto) {
        CatType catType = CatType.builder().type(catTypeDto.getType())
                                           .build();

        catTypeRepository.save(catType);
        return new CatTypeDto();
    }
}
