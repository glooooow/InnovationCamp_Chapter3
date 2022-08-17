package com.sparta.week03.controller;

import com.sparta.week03.domain.Board;
import com.sparta.week03.domain.BoardRepository;
import com.sparta.week03.domain.BoardRequestDto;
import com.sparta.week03.domain.BoardResponseDto;
import com.sparta.week03.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @PostMapping("/boards") // 게시글 작성, 저장
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

    @GetMapping("/boards") // 게시글 전체 조회
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    ///============================== 게시글 편집 =================================///

    @DeleteMapping("/boards/{id}") // 게시글 삭제
    public Long deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제할 글이 존재하지 않습니다.")
        );

        if (!board.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        boardRepository.deleteById(id);
        return id;
    }

    @PutMapping("/boards/{id}") // 게시글 수정
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        boardService.update(id, requestDto);
        return id;
    }

    ///============================== 게시글 개별 조회 =================================///
    @GetMapping("/boards/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }


//    @GetMapping("/api/board/delete/getPassword")
//    @ResponseBody
//    private BoardResponseDto getPassword(@PathVariable Long id, @RequestParam("password") String password) throws Exception{
//        BoardResponseDto boardResponseDto = new BoardResponseDto();
//        boardResponseDto.setId(id);
//        boardResponseDto result = boardService.getContentByid(boardResponseDto);
//        return boardRepository.save(board);
//    }


}


