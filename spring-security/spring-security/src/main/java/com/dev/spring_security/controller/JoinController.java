package com.dev.spring_security.controller;

import com.dev.spring_security.dto.JoinDTO;
import com.dev.spring_security.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinPage() {

        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
