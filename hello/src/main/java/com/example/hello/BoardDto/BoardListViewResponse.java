package com.example.hello.BoardDto;

import com.example.hello.Domain.Board;
import lombok.Getter;

@Getter
public class BoardListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    //전체 게시글 조회
    public BoardListViewResponse(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContnet();
    }


}
