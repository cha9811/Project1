package com.example.hello.BoardController;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {

    @GetMapping("thymeleaf/example")
    public String thymeleafExample(Model model){

        Person examplePerson = new Person();    //뷰로 데이터를 넘겨주는 모델객체
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("게임","독서","운동"));

        model.addAttribute("Person",examplePerson); //person 객체 저장
        model.addAttribute("today", LocalDate.now());

        return "example";   //example.html 뷰 조회
    }

    @Getter
    @Setter
    class Person{
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
