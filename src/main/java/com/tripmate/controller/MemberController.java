package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.MemberDTO;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.MemberService;
import com.tripmate.service.MemberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/member")
@ResponseBody
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

    //todo: 메서드명 수정
    @GetMapping("/duplication/memberId")
    public boolean idDuplicationCheckYn(@RequestParam(value = "memberId") String memberId) {
        return MemberUtil.getDuplicationCheckYn(memberId, ConstCode.DUPLICATION_CHECK_MEMBER_ID);
    }

    @GetMapping("/duplication/nickName")
    public boolean nickNameDuplicationCheckYn(@RequestParam(value = "nickName") String nickName) {
        return MemberUtil.getDuplicationCheckYn(nickName, ConstCode.DUPLICATION_CHECK_NICK_NAME);
    }

    @GetMapping("/duplication/email")
    public boolean emailDuplicationCheckYn(@RequestParam(value = "email") String email) {
        return MemberUtil.getDuplicationCheckYn(email, ConstCode.DUPLICATION_CHECK_EMAIL);
    }

}


