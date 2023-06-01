package com.example.hello.BoardController;

import com.example.hello.BoardDto.BoardListViewResponse;
import com.example.hello.BoardDto.BoardViewResponse;
import com.example.hello.BoardService.BoardService;
import com.example.hello.Domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor //생성자 주입
@Controller
public class BoardViewController {

    private final BoardService boardService;

    //전체조회?
    @GetMapping("/boards")
    public String getBoard(Model model){
        List<BoardListViewResponse> boards = boardService.findAll()
                .stream()
                .map(BoardListViewResponse::new)
                .toList();
        model.addAttribute("boards",boards);
        return "boardList";
    }

    //단일조회
    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable Long id, Model model){
        Board board = boardService.findByID(id);
        model.addAttribute("board", new BoardViewResponse(board));
        return "board";
    }

    @GetMapping("/new-board")
    public String newBoard(@RequestParam(required = false) Long id, Model model){
        if (id == null) {
            //값이 없을경우 (신규 등록이 가능할경우)
            model.addAttribute("Board",new BoardViewResponse());
        } else{
            //값이 있을경우 (신규 등록이 불가능할경우)
            Board board = boardService.findByID(id);
            model.addAttribute("board", new BoardViewResponse(board));
        }
        return "newBoard";
    }
}
