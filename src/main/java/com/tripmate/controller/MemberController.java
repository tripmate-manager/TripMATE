package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.MemberDTO;
import com.tripmate.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/signup")
    public String signUp() {
        return "member/signUp";
    }

    @PostMapping("/callapi-signup")
    public String callApiSignUp(MemberDTO memberDTO) {
        try {
            RetrofitClient.getApiService(MemberService.class).insertMemberInfo(memberDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return "redirect:/signUpResult.trip";
    }

    @GetMapping("/signup-result")
    public String signUpResult() {
        return "member/signUpResult";
    }
}


