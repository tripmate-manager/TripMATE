package com.tripmate.controller;

import com.tripmate.domain.MemberDTO;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.MemberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/member")
@ResponseBody
public class MemberController {

    @GetMapping("/signUp")
    public ModelAndView signUp() {
        return new ModelAndView("member/signUp");
    }

    @PostMapping("/signUp")
    public String signUp(MemberDTO memberDTO) {
        memberDTO.setMemberStatusCode(ConstCode.MEMBER_STATUS_CODE_TEMPORARY);

        int memberNo = MemberUtil.signUp(memberDTO);
        if (memberNo != 0) {
            return "redirect:/signUp/sendEmail";
            //todo: 이메일전송
        }

        return "member/signUp";
    }

    @GetMapping("/duplication/memberId")
    public boolean isIdDuplicate(@RequestParam(value = "memberId") String memberId) {
        return MemberUtil.isDuplicate(memberId, ConstCode.DUPLICATION_CHECK_MEMBER_ID);
    }

    @GetMapping("/duplication/nickName")
    public boolean isNickNameDuplicate(@RequestParam(value = "nickName") String nickName) {
        return MemberUtil.isDuplicate(nickName, ConstCode.DUPLICATION_CHECK_NICK_NAME);
    }

    @GetMapping("/duplication/email")
    public boolean isEmailDuplicate(@RequestParam(value = "email") String email) {
        return MemberUtil.isDuplicate(email, ConstCode.DUPLICATION_CHECK_EMAIL);
    }

    @PostMapping("/signUp/sendEmail")
    public ModelAndView sendSignUpEmail(MemberDTO memberDTO) {

        return new ModelAndView("member/signUpResult");
    }
}


