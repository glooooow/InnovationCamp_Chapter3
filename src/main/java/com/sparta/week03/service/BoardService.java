package com.sparta.week03.service;

import com.sparta.week03.domain.Board;
import com.sparta.week03.domain.BoardRepository;
import com.sparta.week03.domain.BoardRequestDto;
import com.sparta.week03.domain.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정할 글이 존재하지 않습니다.")
        );
        // 비밀번호 일치시에만 게시물 수정가능
        if (!board.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        board.update(requestDto);
        return board.getId();
    }

    public BoardResponseDto getBoard(Long id) {
        Board getBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 글을 찾을 수 없습니다.")
        );
        return new BoardResponseDto(getBoard);

    }

}