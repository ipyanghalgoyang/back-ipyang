package com.project.ipyang.domain.notice.service;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import com.project.ipyang.domain.notice.dto.SelectNoticeDto;
import com.project.ipyang.domain.notice.dto.UpdateNoticeDto;
import com.project.ipyang.domain.notice.dto.WriteNoticeDto;
import com.project.ipyang.domain.notice.dto.NoticeDto;
import com.project.ipyang.domain.notice.entity.Notice;
import com.project.ipyang.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    //안내글 작성
    @Transactional
    public ResponseDto createNotice(IpyangEnum.NoticeCategory selectedCategory,
                                    WriteNoticeDto request,
                                    Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Notice notice = Notice.builder()
                                        .title(request.getTitle())
                                        .content(request.getContent())
                                        .category(selectedCategory)
                                        .member(member)
                                        .build();

        Long savedId = noticeRepository.save(notice).getId();

        if(savedId != null) return new ResponseDto(notice.convertWriteDto(memberId), HttpStatus.OK.value());
        else return new ResponseDto("에러가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    //카테고리별 안내글 조회
    @Transactional
    public ResponseDto<List<SelectNoticeDto>> selectAllNotice(IpyangEnum.NoticeCategory selectedCategory) {
        List<Notice> notices = noticeRepository.findByCategory(selectedCategory);
        List<SelectNoticeDto> selectNoticeDtos = new ArrayList<>();

        if (!notices.isEmpty()) {
            for(Notice notice : notices) {
                SelectNoticeDto selectNoticeDto = notice.convertSelectDto();
                selectNoticeDtos.add(selectNoticeDto);
            }
            return new ResponseDto(selectNoticeDtos, HttpStatus.OK.value());
        } else return new ResponseDto("글이 존재하지 않습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

    }


    //특정 안내글 조회
    @Transactional
    public ResponseDto<SelectNoticeDto> selectNotice(Long id) {
        Optional<Notice> notice = noticeRepository.findById(id);
        if(!notice.isPresent()) {
            return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        SelectNoticeDto detailNotice = notice.get().convertSelectDto();
        return new ResponseDto(detailNotice, HttpStatus.OK.value());
    }


    //특정 안내글 수정
    /*
    * 현재 이미지 수정은 제외하였음
    * */
    @Transactional
    public ResponseDto updateNotice(Long id,
                                    UpdateNoticeDto request) {
        Optional<Notice> notice = noticeRepository.findById(id);
        if(!notice.isPresent()) {
            return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        notice.get().update(request.getTitle(), request.getContent(), request.getCategory());
        return new ResponseDto("수정되었습니다", HttpStatus.OK.value());

    }


    //특정 안내글 삭제
    @Transactional
    public ResponseDto deleteNotice(Long id) {
        Optional<Notice> searchNotice = noticeRepository.findById(id);
        if(!searchNotice.isPresent()) {
            return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else noticeRepository.deleteById(id);

        Optional<Notice> notice = noticeRepository.findById(id);
        if(!notice.isPresent()) {
            return new ResponseDto("삭제되었습니다", HttpStatus.OK.value());
        }
        else return new ResponseDto("에러가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
}
