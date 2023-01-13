package com.tripmate.controller;

import com.tripmate.domain.MailDTO;
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
    public int signUp(MemberDTO memberDTO) {
        memberDTO.setMemberStatusCode(ConstCode.MEMBER_STATUS_CODE_TEMPORARY);

        return MemberUtil.signUp(memberDTO);
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

    @PostMapping("/signUp/sendMail")
    public ModelAndView sendSignUpEmail(MailDTO mailDTO) {
        return new ModelAndView("member/signUp");
    }
}


