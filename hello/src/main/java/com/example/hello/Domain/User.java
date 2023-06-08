package com.example.hello.Domain;

//시큐리티 유저 관련


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", updatable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    @Override   //권한반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        //계정 만료 되었는지 확인용 호직
        return true;    //아직 완료되지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        //계정 잠금여부 확인 로직
        return true;    //아직 안잠김
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //패스워드 만료 여부
        return true;    //아직 살아있음
    }

    @Override
    public boolean isEnabled() {
        //계정 사용 가능여부 반환
        return true;    //아직 안잠김
    }

}
