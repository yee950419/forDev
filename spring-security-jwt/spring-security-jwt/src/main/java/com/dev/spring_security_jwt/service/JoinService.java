package com.dev.spring_security_jwt.service;

import com.dev.spring_security_jwt.dto.JoinDTO;
import com.dev.spring_security_jwt.entity.UserEntity;
import com.dev.spring_security_jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        boolean isUserExist = userRepository.existsByUsername(username);

        if(isUserExist) {

            return;
        }

        UserEntity newUser = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .role("ROLE_USER")
                .build();

        userRepository.save(newUser);
    }
}
