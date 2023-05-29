package springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootdeveloper.domain.Article;
import springbootdeveloper.dto.AddArticleRequest;
import springbootdeveloper.dto.ArticleResponse;
import springbootdeveloper.service.BlogService;

import java.util.List;

@RequiredArgsConstructor
@RestController //HTTP 리스폰스 바디에 객체 데이터를 Json형식으로 반환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService;

    //Http 메서드가 post일경우 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    //요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article saveArticle = blogService.save(request);
        //요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveArticle);
    }

    //전체 글 조회 뒤 반환
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> article = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(article);
    }

    //단일조회
    //url 경로에서 값 추출
    @GetMapping("/api/articles/{id}")
    //상단 매핑이 하단의 @PathVariable로 들어옴
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }
}
