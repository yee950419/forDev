package com.dev.spring_security_jwt.service;

import com.dev.spring_security_jwt.dto.CustomUserDetails;
import com.dev.spring_security_jwt.entity.UserEntity;
import com.dev.spring_security_jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("CUstomUserDetailsService loadUserByUsername");

        UserEntity findUser = userRepository.findByUsername(username);

        if (findUser != null) {

            return new CustomUserDetails(findUser);
        }

        return null;
    }
}
