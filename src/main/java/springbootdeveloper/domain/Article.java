package springbootdeveloper.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", updatable = false)
    private String title;

    @Column(name = "content", updatable = false)
    private String content;

    @Builder// builder 패턴 사용
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

}
