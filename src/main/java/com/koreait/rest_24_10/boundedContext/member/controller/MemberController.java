package com.koreait.rest_24_10.boundedContext.member.controller;

import com.koreait.rest_24_10.boundedContext.member.entity.Member;
import com.koreait.rest_24_10.boundedContext.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/member", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Data
    public static class LoginRequest {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse resp) {
        String accessToken = memberService.genAccessToken(loginRequest.getUsername(), loginRequest.getPassword());

        resp.addHeader("Authentication", accessToken);

        return "응답 본문";
    }

}