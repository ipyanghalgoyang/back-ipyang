package com.project.ipyang.main.service;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.adopt.dto.SelectAdoptDto;
import com.project.ipyang.domain.adopt.entity.Adopt;
import com.project.ipyang.domain.adopt.repository.AdoptRepository;
import com.project.ipyang.domain.board.dto.SelectBoardDto;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.board.repository.BoardRepository;
import com.project.ipyang.domain.product.dto.SelectProductDto;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.product.repository.ProductRepository;
import com.project.ipyang.main.dto.GetTotalDto;
import com.project.ipyang.main.dto.SelectTotalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {
    private final BoardRepository boardRepository;
    private final ProductRepository productRepository;
    private final AdoptRepository adoptRepository;

    @Transactional(readOnly = true)
    public ResponseDto getTotal(int pageNo, int size, String sort) {
        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(Sort.Direction.DESC, sort));

        Page<SelectBoardDto> boards = boardRepository.findAll(pageable).map(SelectBoardDto::new);
        Page<SelectAdoptDto> adopts = adoptRepository.findAll(pageable).map(SelectAdoptDto::new);
        Page<SelectProductDto> products = productRepository.findAll(pageable).map(SelectProductDto::new);

        if(!boards.isEmpty() || !products.isEmpty() || !adopts.isEmpty()) {
            GetTotalDto getTotalDto = new GetTotalDto();

            if(!boards.isEmpty()) getTotalDto.setAdoptDtos(adopts);
            if(!products.isEmpty()) getTotalDto.setProductDtos(products);
            if(!adopts.isEmpty()) getTotalDto.setAdoptDtos(adopts);

            return new ResponseDto(getTotalDto, HttpStatus.OK.value());
        }
        else return new ResponseDto("데이터가 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
