package com.example.hello.Config;

import com.example.hello.BoardService.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor    //생성자 주입
@Configuration  //수동으로 스프링 컨테이너에 빈을 등록하는 설정클래스
public class WebSecurityConfig {

    private final UserDetailService userService;

    //1.스프링 시큐리티 비활성화
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }
    //모든 시큐리티 비활성화 코드로 해당 위치에선 서비스 모든곳에 미적용
    //기본 게시글 뷰는 가능해야 하기에 h2콘솔,static 하위경로는 접근ㅇ ㅣ가능하게 한다.
    //ignoring을 통하여 시큐리티 무시

    //2. 특정 http 요처에 대한 웹기반 보안구성
    //http 메서드를 통해 웹기반 보안을 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeRequests()    //3. 인증 인가 설정 (특정 경로 엑세스)
            //(requestmachers)특정 요청과 일치하는 url의 엑서스 설정.누구나 접근간으(permitall)
            //anyRequest() 상단에 설정한 url 이외에 요청에 대해 설정
            //authenticated() 별도 인가 없이 인증의 접근이 가능
            .requestMatchers("/login", "/signup", "/user").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()    //4. 폼 기반 로그인 설정
            //loginPage - 로그인 페이지 경로 설정
            //defaultSuccessUrl - 로그인이 완료 됫을때 이동할 경로 설정
            .loginPage("/login")
            .defaultSuccessUrl("/boards",true)
            //boards, true시 해당 url로 강제 이동
            .and()
            .logout()   //5. 로그아웃설정
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .and()
            .csrf().disable() //6. csrf 비활성화
            .build();
    }

    //7. 인증 관리자 설정
    //사용자 정보를 가져올 서비스 재정의, 인증바방법, JDBC 기반 인증등 할 때 사용
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService)
        throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)    //8. 사용자 정보 서비스 설정
                //userDetailsService - 사용자 정보를 가져올 서비스 설정
                //passwordEncoder() - 비밀번호 암호화를 위한 인코더 설정
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    //패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
