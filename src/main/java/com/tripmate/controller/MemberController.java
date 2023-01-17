package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.MemberService;
import lombok.NonNull;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/members")
@ResponseBody
public class MemberController {

    @PostMapping("/signUp")
    public boolean signUp(@Valid MemberDTO memberDTO) {
        try {
            memberDTO.setMemberStatusCode(ConstCode.MEMBER_STATUS_CODE_TEMPORARY);

            Call<ResponseWrapper<Integer>> data = RetrofitClient.getApiService(MemberService.class).signUp(memberDTO);
            ResponseWrapper<Integer> response = data.clone().execute().body();

            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    log.warn("response's data size is not 1");
                    throw new IOException("response's data size is not 1");
                }
                if (response.getData().get(0) == 0) {
                    log.warn("response's data is not valid");
                    throw new IOException("response's data is not valid");
                }
            } else {
                log.warn(response.getCode() + " : " + response.getMessage());
                throw new IOException("response code error");
            }
        } catch (NullPointerException | IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }

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

    @GetMapping("/signUp/emailConfirm")
    public ModelAndView signUpMailConfirm(@RequestParam(value = "email") @NonNull @Email String email,
                                          @RequestParam(value = "key") @NonNull @Size(max = 100) String key) {
        try {
            Call<ResponseWrapper> data = RetrofitClient.getApiService(MemberService.class).signUpMailConfirm(email, key);
            ResponseWrapper response = data.clone().execute().body();

            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                return new ModelAndView("members/signUpResult");
            } else {
                log.warn(response.getCode() + " : " + response.getMessage());
                throw new IOException("response code error");
            }
        } catch (NullPointerException | IOException e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("members/signUp");
    }
}

