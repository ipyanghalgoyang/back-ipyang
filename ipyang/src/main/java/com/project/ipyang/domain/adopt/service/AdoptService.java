package com.project.ipyang.domain.adopt.service;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.adopt.dto.*;
import com.project.ipyang.domain.adopt.entity.Adopt;
import com.project.ipyang.domain.catType.entity.CatType;
import com.project.ipyang.domain.vaccine.entity.Vaccine;
import com.project.ipyang.domain.adopt.repository.AdoptRepository;
import com.project.ipyang.domain.catType.repository.CatTypeRepository;
import com.project.ipyang.domain.vaccine.repository.VaccineRepository;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()) return new ResponseDto("로그인이 필요합니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

        Vaccine vaccine = vaccineRepository.findById(request.getVacId()).orElseThrow(()->new IllegalArgumentException("데이터가 존재하지 않습니다."));
        CatType catType = catTypeRepository.findById(request.getCatId()).orElseThrow(()->new IllegalArgumentException("데이터가 존재하지 않습니다."));

        Adopt adopt = Adopt.builder()
                                    .title(request.getTitle())
                                    .content(request.getContent())
                                    .viewCnt(0)
                                    .name(request.getName())
                                    .gender(request.getGender())
                                    .weight(request.getWeight())
                                    .age(request.getAge())
                                    .neu(request.getNeu())
                                    .status(IpyangEnum.Status.N)
                                    .member(member.get())
                                    .vaccine(vaccine)
                                    .catType(catType)
                                    .build();

        Long savedId = adoptRepository.save(adopt).getId();

        if(savedId != null) return new ResponseDto(adopt.convertWriteDto(memberId), HttpStatus.OK.value());
        else return new ResponseDto("에러가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    //전체 입양글 조회 (페이징)
    @Transactional
    public ResponseDto getAdoptList(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;   //요청 받은 페이지
        int pageLimit = 10;                        //페이지당 글 개수
        int blockLimit = 5;                        //한번에 5개의 페이지만 노출

        Page<Adopt> adopts = adoptRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));

        if(!adopts.isEmpty()) {
            Page<AdoptDto> adoptDtos = adopts.map(adopt -> new AdoptDto(adopt.getId(), adopt.getTitle(), adopt.getStatus(),
                                                                        adopt.getMember().getId(), adopt.getMember().getNickname(),
                                                                        adopt.getViewCnt(), adopt.getCreatedAt()));

            int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            int endPage = ((startPage + blockLimit - 1) < adoptDtos.getTotalPages()) ? startPage + blockLimit - 1 : adoptDtos.getTotalPages();

            AdoptPageDto adoptPage = new AdoptPageDto(adoptDtos, startPage, endPage);

            return new ResponseDto(adoptPage, HttpStatus.OK.value());
        }
        else return new ResponseDto("글이 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    //특정 입양글 조회
    @Transactional
    public ResponseDto selectAdopt(Long id) {
        Optional<Adopt> adopt = adoptRepository.findById(id);
        if(!adopt.isPresent()) {
            return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        Adopt findAdopt = adopt.get();
        findAdopt.updateViewCount(findAdopt.getViewCnt());         //조회수 증가
        SelectAdoptDto detailAdopt = findAdopt.convertDto();
        return new ResponseDto(detailAdopt, HttpStatus.OK.value());
    }


    //입양글 수정
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
                           request.getWeight(), request.getAge(), request.getNeu(), request.getStatus(), vaccine, catType);

        return new ResponseDto("수정되었습니다", HttpStatus.OK.value());

    }


    //입양글 삭제
    @Transactional
    public ResponseDto deleteAdopt(Long id) {
        Optional<Adopt> searchAdopt = adoptRepository.findById(id);

        if(!searchAdopt.isPresent()) {
            return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else adoptRepository.deleteById(id);

        Optional<Adopt> adopt = adoptRepository.findById(id);
        if(!adopt.isPresent()) {
            return new ResponseDto("삭제되었습니다", HttpStatus.OK.value());
        }
        else return new ResponseDto("에러가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    //입양 상태, 고양이 품종, 백신 종류에 따라 필터링
    @Transactional
    public ResponseDto filterAdopt(String adopted, List<Long> catIds, List<Long> vacIds, Pageable pageable) {
        int page = pageable.getPageNumber() - 1;                        //요청 받은 페이지
        int blockLimit = 5;                                             //한번에 5개의 페이지만 노출
        pageable = PageRequest.of(page, 10, pageable.getSort());   //페이지당 최대 10개의 데이터 노출

        Page<AdoptDto> adoptDtos = adoptRepository.findFilteredAdopt(adopted, catIds, vacIds, page, pageable);

        if(!adoptDtos.isEmpty()) {
            int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            int endPage = ((startPage + blockLimit - 1) < adoptDtos.getTotalPages()) ? startPage + blockLimit - 1 : adoptDtos.getTotalPages();
            AdoptPageDto adoptFilterPage = new AdoptPageDto(adoptDtos, startPage, endPage);

            return new ResponseDto(adoptFilterPage, HttpStatus.OK.value());
        }
        else return new ResponseDto("글이 존재하지 않습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
