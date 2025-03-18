package com.dev.spring_security_jwt.repository;

import com.dev.spring_security_jwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
