package com.dev.spring_security_jwt.controller;

import com.dev.spring_security_jwt.dto.JoinDTO;
import com.dev.spring_security_jwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);

        return "ok";
    }
}
