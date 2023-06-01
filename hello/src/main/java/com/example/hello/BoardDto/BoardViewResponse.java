package com.example.hello.BoardDto;

import com.example.hello.Domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    //단일조회건
    public BoardViewResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContnet();
        this.createdAt = board.getCreatedAt();
    }
}
