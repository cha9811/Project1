package com.example.hello.BoardDto;

import com.example.hello.Domain.Board;

public class BoardResponse {

    private final String title;
    private final String content;

    public BoardResponse(Board board){
        this.title = board.getTitle();
        this.content = board.getContnet();
    }

}
