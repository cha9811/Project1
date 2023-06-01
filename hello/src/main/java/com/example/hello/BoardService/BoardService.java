package com.example.hello.BoardService;

import com.example.hello.BoardDto.AddBoardRequest;
import com.example.hello.BoardDto.UpdateBoardRequest;
import com.example.hello.BoardRepository.BoardRepository;
import com.example.hello.Domain.Board;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    //저장하기
    public Board save(AddBoardRequest request) {
        return boardRepository.save(request.toEntity());
    }

    //전체조회
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    //단일조회
    public Board findByID(long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        //IllegalArgumentException = 부적합, 부적절 인자를 메서드에 넘길겨웅 발생
    }

    //삭제
    public void delete(long id) {
        boardRepository.deleteById(id);
    }

    //수정하기
    @Transactional
    public Board update(long id, UpdateBoardRequest request){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        board.update(request.getTitle(), request.getContent());

        return board;
    }
}
