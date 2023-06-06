package com.example.hello.BoardController;

import com.example.hello.BoardDto.AddBoardRequest;
import com.example.hello.BoardDto.BoardResponse;
import com.example.hello.BoardDto.UpdateBoardRequest;
import com.example.hello.BoardService.BoardService;
import com.example.hello.Domain.Board;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor //생성자 (autowired 해줌)
@Controller
public class BoardApiController {

    private final BoardService boardService;

    //글작성
    @PostMapping("/api/boards")
    public ResponseEntity<Board> addBoard(@RequestBody AddBoardRequest request) {
        Board savedBoard = boardService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBoard);
    }

    //글 조회
    @GetMapping("/api/boards")
    public ResponseEntity<List<BoardResponse>> findAllBoards() {
        List<BoardResponse> boards = boardService.findAll()
                .stream()
                .map(BoardResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(boards);
    }

    //단일조회
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable Long id) {
        Board board = boardService.findByID(id);

        return ResponseEntity.ok()
                .body(new BoardResponse(board));
    }

    //단일삭제
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long id) {
        boardService.delete(id);
        return ResponseEntity.ok()
                .build();

    }
    //단일 업뎃
    @PutMapping("/api/boards/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable long id, @RequestBody UpdateBoardRequest request){
        Board updateBoard = boardService.update(id,request);

        return ResponseEntity.ok()
                .body(updateBoard);

    }

}
