package com.dev.spring_security.service;

import com.dev.spring_security.dto.CustomUserDetails;
import com.dev.spring_security.entity.UserEntity;
import com.dev.spring_security.repository.UserRepository;
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

        UserEntity findUser = userRepository.findByUsername(username);

        if(findUser != null) {
            return new CustomUserDetails(findUser);
        }

        return null;
    }

}
