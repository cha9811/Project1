package com.example.hello.BoardService;

import com.example.hello.BoardDto.AddUserRequest;
import com.example.hello.BoardRepository.UserRepository;
import com.example.hello.Domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //BCryptPasswordEncoder - 인코딩이 자체 내장된 메서드
    public Long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                //패스워드 암호화 - 저장시 인코딩으로 등록한 빈을 사용해 암호화후 저장
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();

    }

}
