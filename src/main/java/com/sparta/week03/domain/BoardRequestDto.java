package com.sparta.week03.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class BoardRequestDto {
    private String title;           //글제목
    private String contents;        //내용
    private String author;          //글쓴이
    private Long password;          //비밀번호


}
