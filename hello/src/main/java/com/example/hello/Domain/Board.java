package com.example.hello.Domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //PK값 자동 1씩 상승
    @Column(name = "id", updatable = false)
    //updatable = true (엔티티 수정시 해당필드 DB에 저장) / false = DB에 미저장)]
    //insertable = true (엔티티 저장시 해당필드 DB에 저장) / false = DB에 미저장
    private Long id;

    @Column(name = "title", updatable = false)
    private String title;

    @Column(name = "content", updatable = false)
    private String contnet;

    @Column(name = "comment", updatable = false)
    private String comment;

    @CreatedDate
    //생성된 시간 정보
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    //LocalDataTime - 현재 로컬컴퓨터의 날짜 및 시간을 반환

    @LastModifiedBy
    @Column(name = "updated_at")
    private  LocalDateTime updateAt;
    //todo : 로컬타임 말고 서버시간으로 저장하는 방법은??

    //글생성
    @Builder
    public Board(String title, String content){
        this.title = title;
        this.contnet = content;
    }

    //글 업뎃
    public void update(String title, String content){
        this.title = title;
        this.contnet = content;

    }
}
