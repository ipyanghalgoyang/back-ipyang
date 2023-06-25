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
import com.project.ipyang.main.dto.SelectTotalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {
    private final BoardRepository boardRepository;
    private final ProductRepository productRepository;
    private final AdoptRepository adoptRepository;

    //전체 게시판 게시글, 상품, 입양글 데이터 가져오기
    public ResponseDto
    selectTotal(SelectTotalDto selectTotalDto) {
        List<Board> boards = boardRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Adopt> adopts = adoptRepository.findAll();

        if(!boards.isEmpty() || !products.isEmpty() || !adopts.isEmpty()) {

            //가져올 게시글 데이터가 존재하는 경우
            if(!boards.isEmpty()) {
                List<SelectBoardDto> boardDtos = new ArrayList<>();
                for(Board board : boards) {
                    SelectBoardDto selectBoardDto = board.convertDto();
                    boardDtos.add(selectBoardDto);
                }
                selectTotalDto.setBoardDtos(boardDtos);
            }

            //가져올 상품 데이터가 존재하는 경우
            if(!products.isEmpty()) {
                List<SelectProductDto> productDtos = new ArrayList<>();
                for(Product product : products) {
                    SelectProductDto selectProductDto = product.convertDto();
                    productDtos.add(selectProductDto);
                }
                selectTotalDto.setProductDtos(productDtos);
            }

            //가져올 입양글 데이터가 존재하는 경우
            if(!adopts.isEmpty()) {
                List<SelectAdoptDto> adoptDtos = new ArrayList<>();
                for(Adopt adopt : adopts) {
                    SelectAdoptDto selectAdoptDto = adopt.convertDto();
                    adoptDtos.add(selectAdoptDto);
                }
                selectTotalDto.setAdoptDtos(adoptDtos);
            }
            return new ResponseDto(selectTotalDto, HttpStatus.OK.value());

        } else return new ResponseDto("가져올 데이터가 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
}
