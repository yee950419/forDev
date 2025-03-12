package com.dev.spring_security.service;

import com.dev.spring_security.dto.JoinDTO;
import com.dev.spring_security.entity.UserEntity;
import com.dev.spring_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        // DB에 같은 사람이 존재하는지 검증, 이후 새로운 유저 생성

        UserEntity newUser = UserEntity.builder()
                .username(joinDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(newUser);
    }
}
