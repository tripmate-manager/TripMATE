package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Call;

import javax.validation.Valid;
import java.io.IOException;

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
    public boolean signUp(@Valid MemberDTO memberDTO) {
        int memberNo;

        try {
            memberDTO.setMemberStatusCode(ConstCode.MEMBER_STATUS_CODE_TEMPORARY);

            Call<ResponseWrapper<Integer>> data = RetrofitClient.getApiService(MemberService.class).signUp(memberDTO);
            ResponseWrapper<Integer> response = data.clone().execute().body();

            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    log.warn("response's data size is not 1");
                    throw new IOException("response's data size is not 1");
                } else {
                    memberNo = response.getData().get(0);
                }
            } else {
                log.warn(response.getCode() + " : " + response.getMessage());
                throw new IOException("response code error");
            }
        } catch (NullPointerException | IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }

        // 메일전송

        return true;
    }

    @GetMapping("/duplication/memberId")
    public boolean isIdDuplicate(@RequestParam(value = "memberId") String memberId) {
        boolean isIdDuplicate = false;

        try {
            Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(MemberService.class).isIdDuplicate(memberId);
            ResponseWrapper<Boolean> response = data.clone().execute().body();

            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    log.warn("response's data size is not 1");
                    throw new IOException("response's data size is not 1");
                } else {
                    isIdDuplicate = response.getData().get(0);
                }
            } else {
                log.warn(response.getCode() + " : " + response.getMessage());
                throw new IOException("response code error");
            }
        } catch (NullPointerException | IOException e) {
            log.error(e.getMessage(), e);
        }

        return isIdDuplicate;
    }

    @GetMapping("/duplication/nickName")
    public boolean isNickNameDuplicate(@RequestParam(value = "nickName") String nickName) {
        boolean isNickNameDuplicate = false;

        try {
            Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(MemberService.class).isNickNameDuplicate(nickName);
            ResponseWrapper<Boolean> response = data.clone().execute().body();

            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    log.warn("response's data size is not 1");
                    throw new IOException("response's data size is not 1");
                } else {
                    isNickNameDuplicate = response.getData().get(0);
                }
            } else {
                log.warn(response.getCode() + " : " + response.getMessage());
                throw new IOException("response code error");
            }
        } catch (NullPointerException | IOException e) {
            log.error(e.getMessage(), e);
        }

        return isNickNameDuplicate;
    }

    @GetMapping("/duplication/email")
    public boolean isEmailDuplicate(@RequestParam(value = "email") String email) {
        boolean isEmailDuplicate = false;

        try {
            Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(MemberService.class).isEmailDuplicate(email);
            ResponseWrapper<Boolean> response = data.clone().execute().body();

            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    log.warn("response's data size is not 1");
                    throw new IOException("response's data size is not 1");
                } else {
                    isEmailDuplicate = response.getData().get(0);
                }
            } else {
                log.warn(response.getCode() + " : " + response.getMessage());
                throw new IOException("response code error");
            }
        } catch (NullPointerException | IOException e) {
            log.error(e.getMessage(), e);
        }

        return isEmailDuplicate;
    }

    @GetMapping("/signUp/signUpResult")
    public ModelAndView sendSignUpMail() {
        return new ModelAndView("member/signUpResult");
    }
}


