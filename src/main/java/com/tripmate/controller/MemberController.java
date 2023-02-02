package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.ChangePasswordDTO;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.MemberMailDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.SignInDTO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.Const;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Call;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping(value = "/members", produces = "application/json; charset=utf8")
public class MemberController {

    @PostMapping("/signUp")
    public @ResponseBody String signUp(@Valid MemberDTO memberDTO) {
        ApiResult result;
        try {
            memberDTO.setMemberStatusCode(ConstCode.MEMBER_STATUS_CODE_TEMPORARY);

            Call<ResponseWrapper<Integer>> data = RetrofitClient.getApiService(MemberService.class).signUp(memberDTO);
            ResponseWrapper<Integer> response = data.clone().execute().body();

            if (ObjectUtils.isEmpty(response)) {
                throw new IOException("api response data is empty");
            } else {
                if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                    if (response.getData().size() != 1) {
                        throw new IOException("response's data size is not 1");
                    }
                    if (response.getData().get(0) == 0) {
                        throw new IOException("response's data is not valid");
                    }

                    log.debug("member no is {}", response.getData().get(0));
                }

                result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @GetMapping("/duplication/memberId")
    public @ResponseBody String isIdDuplicate(@RequestParam(value = "memberId") String memberId) {
        return isDuplicate(ConstCode.DUPLICATION_CHECK_MEMBER_ID, memberId);
    }

    @GetMapping("/duplication/nickName")
    public @ResponseBody String isNickNameDuplicate(@RequestParam(value = "nickName") String nickName) {
        return isDuplicate(ConstCode.DUPLICATION_CHECK_NICK_NAME, nickName);
    }

    @GetMapping("/duplication/email")
    public @ResponseBody String isEmailDuplicate(@RequestParam(value = "email") String email) {
        return isDuplicate(ConstCode.DUPLICATION_CHECK_EMAIL, email);
    }

    private String isDuplicate(final String type, final String value) {
        ApiResult result;

        try {
            Call<ResponseWrapper<Boolean>> data;

            switch (type) {
                case ConstCode.DUPLICATION_CHECK_MEMBER_ID:
                    data = RetrofitClient.getApiService(MemberService.class).isIdDuplicate(value);
                    break;
                case ConstCode.DUPLICATION_CHECK_NICK_NAME:
                    data = RetrofitClient.getApiService(MemberService.class).isNickNameDuplicate(value);
                    break;
                default:
                    data = RetrofitClient.getApiService(MemberService.class).isEmailDuplicate(value);
            }

            ResponseWrapper<Boolean> response = data.clone().execute().body();

            if (ObjectUtils.isEmpty(response)) {
                throw new IOException("api response data is empty");
            } else {
                if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                    if (response.getData().size() != 1) {
                        throw new IOException("response's data size is not 1");
                    }
                }
                result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
                result.put("isDuplicate", response.getData().get(0).booleanValue());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @GetMapping("/signUp/emailConfirm")
    public ModelAndView certificationMailConfirm(@RequestParam(value = "memberId") @NotBlank @Size(min = 5, max = 20) String memberId,
                                                 @RequestParam(value = "key") @NotBlank @Max(100) String key,
                                                 @RequestParam(value = "mailTypeCode") @NotBlank @Pattern(regexp = "^[12]0$") String mailTypeCode) {
        try {
            Call<ResponseWrapper> data = RetrofitClient.getApiService(MemberService.class).certificationMailConfirm(memberId, key, mailTypeCode);
            ResponseWrapper response = data.clone().execute().body();

            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                return new ModelAndView("members/signIn");
            } else {
                log.warn(response.getCode() + " : " + response.getMessage());
                throw new IOException("response code error");
            }
        } catch (NullPointerException | IOException e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("members/signUp");
    }

    @PostMapping("/signIn")
    public @ResponseBody String signIn(HttpServletRequest request, @Valid SignInDTO signInDTO) {
        ApiResult result;

        try {
            Call<ResponseWrapper<MemberDTO>> data = RetrofitClient.getApiService(MemberService.class).signIn(signInDTO);
            ResponseWrapper<MemberDTO> response = data.clone().execute().body();

            if (ObjectUtils.isEmpty(response)) {
                throw new IOException("api response data is empty");
            } else {
                result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();

                if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                    if (response.getData().size() != 1) {
                        throw new IOException("response's data size is not 1");
                    }
                    if (ObjectUtils.isEmpty(response.getData().get(0))) {
                        throw new IOException("response's data is Empty");
                    }
                    MemberDTO memberDTO = response.getData().get(0);

                    if (memberDTO.getSignInRequestCnt() >= Const.SIGNIN_LIMIT_CNT) {
                        result.put("signInRequestCnt", memberDTO.getSignInRequestCnt());
                        return result.toJson();
                    }

                    if (ConstCode.MEMBER_STATUS_CODE_COMPLETE.equals(memberDTO.getMemberStatusCode()) || ConstCode.MEMBER_STATUS_CODE_ISSUE_TEMPORARY_PASSWORD.equals(memberDTO.getMemberStatusCode())) {
                        HttpSession session = request.getSession();
                        session.setAttribute(Const.MEMBER_INFO_SESSION, memberDTO);
                    }

                    result.put("memberStatusCode", memberDTO.getMemberStatusCode());
                    result.put("email", memberDTO.getEmail());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/temporarySignInResult")
    public ModelAndView temporarySignInResult(@Valid SignInDTO signInDTO) {
        return new ModelAndView("members/temporarySignInResult").addObject("signInInfo", signInDTO);
    }

    @PostMapping("/changeEmail")
    public ModelAndView changeEmail(@Valid SignInDTO signInDTO) {
        return new ModelAndView("members/changeEmail").addObject("signInInfo", signInDTO);
    }

    @GetMapping("/findId")
    public String findId(@RequestParam(value = "memberName") @NotBlank @Max(20) String memberName,
                         @RequestParam(value = "email") @NotBlank @Email String email) {
        ApiResult result;

        try {
            Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(MemberService.class).findId(memberName, email);
            ResponseWrapper<String> response = data.clone().execute().body();

            if (ObjectUtils.isEmpty(response)) {
                throw new IOException("response is Empty");
            }
            result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    throw new IOException("response's data size is not 1");
                }
                if (ObjectUtils.isEmpty(response.getData().get(0))) {
                    throw new IOException("response's data is Empty");
                }

                String memberId = response.getData().get(0);
                result.put("memberId", memberId.substring(0, memberId.length() - 4) + "****");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/sendCertificationMail")
    public @ResponseBody String sendCertificationMail(@Valid MemberMailDTO memberMailDTO) {
        return isSendMailSuccess(ConstCode.EMAIL_TYPE_CODE_JOIN, memberMailDTO);
    }

    @PostMapping("/sendPasswordMail")
    public @ResponseBody String sendPasswordMail(@Valid MemberMailDTO memberMailDTO) {
        return isSendMailSuccess(memberMailDTO.getMailTypeCode(), memberMailDTO);
    }

    private String isSendMailSuccess(final String type, final MemberMailDTO memberMailDTO) {
        ApiResult result;

        try {
            Call<ResponseWrapper<Boolean>> data;
            switch (type) {
                case ConstCode.EMAIL_TYPE_CODE_JOIN:
                case ConstCode.EMAIL_TYPE_CODE_EMAIL_CHANGE:
                    data = RetrofitClient.getApiService(MemberService.class).sendCertificationMail(memberMailDTO);
                    break;
                default:
                    data = RetrofitClient.getApiService(MemberService.class).sendPasswordMail(memberMailDTO);
            }
            ResponseWrapper<Boolean> response = data.clone().execute().body();

            if (ObjectUtils.isEmpty(response)) {
                throw new IOException("response is Empty");
            }
            result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    throw new IOException("response's data size is not 1");
                }
                if (ObjectUtils.isEmpty(response.getData().get(0))) {
                    throw new IOException("response's data is Empty");
                }

                result.put("sendMailSuccess", response.getData().get(0));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/changePassword")
    public @ResponseBody String changePassword(HttpServletRequest request, @Valid ChangePasswordDTO changePasswordDTO) {
        ApiResult result;
        MemberDTO sessionDTO = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);

        try {
            if (ObjectUtils.isEmpty(sessionDTO)) {
                throw new IOException("session is Empty");
            }
            ChangePasswordDTO changePasswordRequestDTO = ChangePasswordDTO.builder()
                    .memberNo(sessionDTO.getMemberNo())
                    .memberId(sessionDTO.getMemberId())
                    .memberPassword(changePasswordDTO.getMemberPassword())
                    .newMemberPassword(changePasswordDTO.getNewMemberPassword())
                    .build();

            Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(MemberService.class).changePassword(changePasswordRequestDTO);
            ResponseWrapper<Boolean> response = data.clone().execute().body();

            if (ObjectUtils.isEmpty(response)) {
                throw new IOException("response is Empty");
            }

            result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    throw new IOException("response's data size is not 1");
                }
                if (ObjectUtils.isEmpty(response.getData().get(0))) {
                    throw new IOException("response's data is Empty");
                }
                result.put("changePasswordSuccess", response.getData().get(0));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}
