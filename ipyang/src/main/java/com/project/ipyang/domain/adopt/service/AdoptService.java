package com.project.ipyang.domain.adopt.service;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.adopt.dto.AdoptDto;
import com.project.ipyang.domain.adopt.dto.SelectAdoptDto;
import com.project.ipyang.domain.adopt.dto.UpdateAdoptDto;
import com.project.ipyang.domain.adopt.dto.WriteAdoptDto;
import com.project.ipyang.domain.adopt.entity.Adopt;
import com.project.ipyang.domain.catType.entity.CatType;
import com.project.ipyang.domain.vaccine.entity.Vaccine;
import com.project.ipyang.domain.adopt.repository.AdoptRepository;
import com.project.ipyang.domain.catType.repository.CatTypeRepository;
import com.project.ipyang.domain.vaccine.repository.VaccineRepository;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //입양글 작성
    @Transactional
    public ResponseDto createAdopt(WriteAdoptDto request, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Vaccine vaccine = vaccineRepository.findById(request.getVacId()).orElseThrow(()->new IllegalArgumentException("데이터가 존재하지 않습니다."));
        CatType catType = catTypeRepository.findById(request.getCatId()).orElseThrow(()->new IllegalArgumentException("데이터가 존재하지 않습니다."));

        Adopt adopt = Adopt.builder()
                                    .title(request.getTitle())
                                    .content(request.getContent())
                                    .view(0)
                                    .name(request.getName())
                                    .gender(request.getGender())
                                    .weight(request.getWeight())
                                    .age(request.getAge())
                                    .neu(request.getNeu())
                                    .yn(0)
                                    .member(member)
                                    .vaccine(vaccine)
                                    .catType(catType)
                                    .build();

        Long savedId = adoptRepository.save(adopt).getId();

        if(savedId != null) return new ResponseDto(adopt.convertWriteDto(memberId), HttpStatus.OK.value());
        else return new ResponseDto("에러가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    //전체 입양글 조회
    @Transactional
    public ResponseDto selectAllAdopt(SelectAdoptDto request) {
        List<Adopt> adopts = adoptRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        List<SelectAdoptDto> selectAdoptDtos = adopts.stream().map(SelectAdoptDto::new).collect(Collectors.toList());

        if (!selectAdoptDtos.isEmpty()) {
            return new ResponseDto(selectAdoptDtos, HttpStatus.OK.value());
        } else return new ResponseDto("가져올 데이터가 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    //특정 입양글 조회
    @Transactional
    public ResponseDto selectAdopt(Long id) {
        Optional<Adopt> adopt = adoptRepository.findById(id);
        if(!adopt.isPresent()) {
            return new ResponseDto("가져올 데이터가 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        SelectAdoptDto detailAdopt = adopt.get().convertDto();
        return new ResponseDto(detailAdopt, HttpStatus.OK.value());
    }


    //특정 입양글 수정
    /*
    * 현재 이미지 수정은 제외하였음
    * */
    @Transactional
    public ResponseDto updateAdopt(Long id, UpdateAdoptDto request) {
        Optional<Adopt> adopt = adoptRepository.findById(id);
        if(!adopt.isPresent()) {
            return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        Vaccine vaccine = vaccineRepository.findById(request.getVacId()).orElseThrow(()->new IllegalArgumentException("데이터가 존재하지 않습니다."));
        CatType catType = catTypeRepository.findById(request.getCatId()).orElseThrow(()->new IllegalArgumentException("데이터가 존재하지 않습니다."));

        adopt.get().update(request.getTitle(), request.getContent(), request.getName(), request.getGender(),
                           request.getWeight(), request.getAge(), request.getNeu(), request.getYn(), vaccine, catType);

        return new ResponseDto("수정되었습니다", HttpStatus.OK.value());

    }


    //특정 입양글 삭제
    @Transactional
    public ResponseDto deleteAdopt(Long id) {
        Optional<Adopt> Searchadopt = adoptRepository.findById(id);

        if(!Searchadopt.isPresent()) {
            return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else adoptRepository.deleteById(id);

        Optional<Adopt> adopt = adoptRepository.findById(id);
        if(!adopt.isPresent()) {
            return new ResponseDto("삭제되었습니다", HttpStatus.OK.value());
        }
        else return new ResponseDto("에러가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
