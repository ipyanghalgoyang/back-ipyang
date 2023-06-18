package com.project.ipyang.domain.vaccine.service;

import com.project.ipyang.domain.vaccine.dto.InsertVaccineDto;
import com.project.ipyang.domain.vaccine.dto.VaccineDto;
import com.project.ipyang.domain.vaccine.entity.Vaccine;
import com.project.ipyang.domain.vaccine.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    public VaccineDto createVaccine(InsertVaccineDto vaccineDto) {
        Vaccine vaccine = Vaccine.builder().name(vaccineDto.getName()).build();
        vaccineRepository.save(vaccine);

        return new VaccineDto();
    }
}
