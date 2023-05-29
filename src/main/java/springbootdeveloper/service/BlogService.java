package springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootdeveloper.domain.Article;
import springbootdeveloper.dto.AddArticleRequest;
import springbootdeveloper.repository.BlogRepository;

import java.util.List;

@RequiredArgsConstructor    //fianl이 붙거나 @Notnull이 붙은 필드의 생성자 추가
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    //전체 가져오기
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    //글 1개 조회 메서드
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : "+ id));
    }
}
