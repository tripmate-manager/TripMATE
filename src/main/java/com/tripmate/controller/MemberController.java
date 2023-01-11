package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.MemberDTO;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/signUp")
    public String signUp() {
        return "member/signUp";
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(MemberDTO memberDTO) {
        ModelAndView mav = new ModelAndView("member/signUpResult");
        memberDTO.setMemberStatusCode(ConstCode.MEMBER_STATUS_CODE_TEMPORARY);

        try {
            RetrofitClient.getApiService(MemberService.class).insertMemberInfo(memberDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return mav;
    }
}


