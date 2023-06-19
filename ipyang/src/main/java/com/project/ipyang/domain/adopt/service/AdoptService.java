package com.project.ipyang.domain.adopt.service;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.adopt.dto.AdoptDto;
import com.project.ipyang.domain.adopt.dto.SelectAdoptDto;
import com.project.ipyang.domain.adopt.dto.InsertAdoptDto;
import com.project.ipyang.domain.adopt.entity.Adopt;
import com.project.ipyang.domain.catType.entity.CatType;
import com.project.ipyang.domain.vaccine.entity.Vaccine;
import com.project.ipyang.domain.adopt.repository.AdoptRepository;
import com.project.ipyang.domain.catType.repository.CatTypeRepository;
import com.project.ipyang.domain.vaccine.repository.VaccineRepository;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdoptService {

    private final AdoptRepository adoptRepository;
    private final MemberRepository memberRepository;
    private final VaccineRepository vaccineRepository;
    private final CatTypeRepository catTypeRepository;

    //입양 게시글 데이터 삽입
    public AdoptDto createAdopt(InsertAdoptDto adoptDto) {
        Member member = memberRepository.findById(adoptDto.getMember_id()).get();
        Vaccine vaccine = vaccineRepository.findById(adoptDto.getVaccine_id()).get();
        CatType catType = catTypeRepository.findById(adoptDto.getCat_id()).get();

        Adopt adopt = Adopt.builder().title(adoptDto.getTitle())
                                     .content(adoptDto.getContent())
                                     .view(adoptDto.getView())
                                     .name(adoptDto.getName())
                                     .gender(adoptDto.getGender())
                                     .weight(adoptDto.getWeight())
                                     .age(adoptDto.getAge())
                                     .neu(adoptDto.getNeu())
                                     .yn(adoptDto.getYn())
                                     .member(member)
                                     .vaccine(vaccine)
                                     .catType(catType)
                                     .build();

        adoptRepository.save(adopt);
        return new AdoptDto();
    }

    //전체 입양 게시글 가져오기
    public ResponseDto selectAllAdopt(SelectAdoptDto request) {
        List<Adopt> adopts = adoptRepository.findAll();
        List<SelectAdoptDto> selectAdoptDtos = adopts.stream().map(SelectAdoptDto::new).collect(Collectors.toList());

        if (!selectAdoptDtos.isEmpty()) {
            return new ResponseDto(selectAdoptDtos, HttpStatus.OK.value());
        } else return new ResponseDto("가져올 데이터가 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
