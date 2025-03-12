package com.dev.spring_security.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    private String role;

    @Builder
    public UserEntity(String username, String password, String role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }
}
