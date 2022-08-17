package com.sparta.week03.domain;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@Getter
@NoArgsConstructor

public class BoardResponseDto extends Timestamped {
    private String title;           //글제목
    private String contents;        //내용
    private String author;          //글쓴이

    @JsonIgnore
    private Long password;          //비밀번호

//    @CreatedDate
//    private LocalDateTime createdAt;

    public BoardResponseDto(Board getBoard) {
        super.createdAt = getBoard.createdAt;
        this.title = getBoard.getTitle();
        this.contents = getBoard.getContents();
        this.author = getBoard.getAuthor();
        this.password = getBoard.getPassword();

}

}
