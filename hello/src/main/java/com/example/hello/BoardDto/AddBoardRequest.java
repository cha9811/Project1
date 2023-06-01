package com.example.hello.BoardDto;

import com.example.hello.Domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
//모든 필드 값을 파라미터로 받는 생성자로 만듦
@NoArgsConstructor
//파라미터가 없는 기본 생성자를 생성
public class AddBoardRequest {
    private String title;
    private String content;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
//User user1 = new User(); // @NoArgsConstructor
//User user2 = new User("user2", "1234"); // @RequiredArgsConstructor
//User user3 = new User(1L, "user3", "1234", null); // @AllArgsConstructor
