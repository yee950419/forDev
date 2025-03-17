package com.dev.spring_security_jwt.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

}
