package com.tripmate.controller;

import com.tripmate.domain.ChangePasswordDTO;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.MemberMailDTO;
import com.tripmate.domain.MypageDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.SignInDTO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.Const;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.apiservice.MemberApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    private final MemberApiService memberApiService;

    @Autowired
    public MemberController(MemberApiService memberApiService) {
        this.memberApiService = memberApiService;
    }

    @PostMapping("/signUp")
    public @ResponseBody String signUp(@Valid MemberDTO memberDTO) {
        ApiResult result;
        try {
            memberDTO.setMemberStatusCode(ConstCode.MEMBER_STATUS_CODE_TEMPORARY);

            ResponseWrapper<Integer> response = memberApiService.signUp(memberDTO);

            if (response == null) {
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
            ResponseWrapper<Boolean> response = memberApiService.isDuplicate(type, value);

            if (response == null) {
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

    @GetMapping("/emailConfirm")
    public ModelAndView certificationMailConfirm(HttpServletRequest request,
                                                 @RequestParam(value = "memberId") @NotBlank @Size(min = 5, max = 20) String memberId,
                                                 @RequestParam(value = "key") @NotBlank @Max(100) String key,
                                                 @RequestParam(value = "mailTypeCode") @NotBlank @Pattern(regexp = "^[123]0$") String mailTypeCode) {
        try {
            ResponseWrapper<String> response = memberApiService.certificationMailConfirm(memberId, key, mailTypeCode);

            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    throw new IOException("response's data size is not 1");
                }
                if (response.getData().get(0) == null) {
                    throw new IOException("response's data is Empty");
                }

                if (ConstCode.EMAIL_TYPE_CODE_EMAIL_CHANGE.equals(mailTypeCode)) {
                    MemberDTO memberInfoSession = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);
                    MemberDTO memberDTO = MemberDTO.builder()
                            .memberNo(memberInfoSession.getMemberNo())
                            .memberId(memberInfoSession.getMemberId())
                            .memberPassword(memberInfoSession.getMemberPassword())
                            .memberName(memberInfoSession.getMemberName())
                            .nickName(memberInfoSession.getNickName())
                            .email(response.getData().get(0))
                            .genderCode(memberInfoSession.getGenderCode())
                            .birthDay(memberInfoSession.getBirthDay())
                            .memberStatusCode(memberInfoSession.getMemberStatusCode())
                            .signInRequestCnt(memberInfoSession.getSignInRequestCnt())
                            .build();
                    request.getSession().setAttribute(Const.MEMBER_INFO_SESSION, memberDTO);
                }

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
            ResponseWrapper<MemberDTO> response = memberApiService.signIn(signInDTO);

            if (response == null) {
                throw new IOException("api response data is empty");
            } else {
                result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();

                if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                    if (response.getData().size() != 1) {
                        throw new IOException("response's data size is not 1");
                    }
                    if (response.getData().get(0) == null) {
                        throw new IOException("response's data is Empty");
                    }
                    MemberDTO memberDTO = response.getData().get(0);

                    if (memberDTO.getSignInRequestCnt() >= Const.SIGNIN_LIMIT_CNT) {
                        result.put("signInRequestCnt", memberDTO.getSignInRequestCnt());
                        return result.toJson();
                    }

                    if (ConstCode.MEMBER_STATUS_CODE_COMPLETE.equals(memberDTO.getMemberStatusCode()) || ConstCode.MEMBER_STATUS_CODE_ISSUE_TEMPORARY_PASSWORD.equals(memberDTO.getMemberStatusCode())) {
                        request.getSession().setAttribute(Const.MEMBER_INFO_SESSION, memberDTO);
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
    public @ResponseBody String findId(@RequestParam(value = "memberName") @NotBlank @Max(20) String memberName,
                         @RequestParam(value = "email") @NotBlank @Email String email) {
        ApiResult result;

        try {
            ResponseWrapper<String> response = memberApiService.findId(memberName, email);

            if (response == null) {
                throw new IOException("response is Empty");
            }

            result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    throw new IOException("response's data size is not 1");
                }
                if (response.getData().get(0) == null) {
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
        return isSendMailSuccess(memberMailDTO.getMailTypeCode(), memberMailDTO);
    }

    @PostMapping("/sendPasswordMail")
    public @ResponseBody String sendPasswordMail(@Valid MemberMailDTO memberMailDTO) {
        return isSendMailSuccess(memberMailDTO.getMailTypeCode(), memberMailDTO);
    }

    private String isSendMailSuccess(final String type, final MemberMailDTO memberMailDTO) {
        ApiResult result;

        try {
            ResponseWrapper<Boolean> response = memberApiService.isSendMailSuccess(type, memberMailDTO);

            if (response == null) {
                throw new IOException("response is Empty");
            }
            result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    throw new IOException("response's data size is not 1");
                }
                if (response.getData().get(0) == null) {
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
        MemberDTO memberInfoSession = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);

        try {
            if (memberInfoSession == null) {
                throw new IOException("session is Empty");
            }
            ChangePasswordDTO changePasswordRequestDTO = ChangePasswordDTO.builder()
                    .memberNo(memberInfoSession.getMemberNo())
                    .memberId(memberInfoSession.getMemberId())
                    .memberPassword(changePasswordDTO.getMemberPassword())
                    .newMemberPassword(changePasswordDTO.getNewMemberPassword())
                    .build();

            ResponseWrapper<Boolean> response = memberApiService.changePassword(changePasswordRequestDTO);

            if (response == null) {
                throw new IOException("response is Empty");
            }

            result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    throw new IOException("response's data size is not 1");
                }
                if (response.getData().get(0) == null) {
                    throw new IOException("response's data is Empty");
                }

                MemberDTO memberDTO = MemberDTO.builder()
                        .memberNo(memberInfoSession.getMemberNo())
                        .memberId(memberInfoSession.getMemberId())
                        .memberPassword(changePasswordDTO.getNewMemberPassword())
                        .memberName(memberInfoSession.getMemberName())
                        .nickName(memberInfoSession.getNickName())
                        .email(memberInfoSession.getEmail())
                        .genderCode(memberInfoSession.getGenderCode())
                        .birthDay(memberInfoSession.getBirthDay())
                        .memberStatusCode(memberInfoSession.getMemberStatusCode())
                        .signInRequestCnt(memberInfoSession.getSignInRequestCnt())
                        .build();
                request.getSession().setAttribute(Const.MEMBER_INFO_SESSION, memberDTO);

                result.put("changePasswordSuccess", response.getData().get(0));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/signOut")
    public String signOut(HttpServletRequest request) {
        request.getSession().invalidate();

        return "redirect:/forward/main/main.trip";
    }

    @PostMapping("/withdraw")
    public @ResponseBody String withdraw(HttpServletRequest request) {
        ApiResult result;
        MemberDTO sessionDTO = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);

        try {
            if (sessionDTO == null) {
                throw new IOException("session is Empty");
            }

            ResponseWrapper<Boolean> response = memberApiService.withdraw(sessionDTO.getMemberNo());

            if (response == null) {
                throw new IOException("response is Empty");
            }

            result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    throw new IOException("response's data size is not 1");
                }
                if (response.getData().get(0) == null) {
                    throw new IOException("response's data is Empty");
                }

                request.getSession().invalidate();
                result.put("withDrawSuccess", response.getData().get(0));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/editMemberInfo")
    public @ResponseBody String editMypageMemberInfo(HttpServletRequest request, @Valid MypageDTO mypageDTO) {
        ApiResult result;
        MemberDTO memberInfoSession = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);

        try {
            if (memberInfoSession == null) {
                throw new IOException("session is Empty");
            }

            MypageDTO requestMypageInfo = MypageDTO.builder()
                    .memberNo(memberInfoSession.getMemberNo())
                    .nickName(mypageDTO.getNickName())
                    .birthDay(mypageDTO.getBirthDay())
                    .genderCode(mypageDTO.getGenderCode()).build();

            ResponseWrapper<MypageDTO> response = memberApiService.editMypageMemberInfo(requestMypageInfo.getMemberNo(), requestMypageInfo);

            if (response == null) {
                throw new IOException("response is Empty");
            }

            result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    throw new IOException("response's data size is not 1");
                }
                if (response.getData().get(0) == null) {
                    throw new IOException("response's data is Empty");
                }

                MypageDTO resultMypageInfo = response.getData().get(0);

                MemberDTO memberDTO = MemberDTO.builder()
                        .memberNo(resultMypageInfo.getMemberNo())
                        .memberId(memberInfoSession.getMemberId())
                        .memberPassword(memberInfoSession.getMemberPassword())
                        .memberName(memberInfoSession.getMemberName())
                        .nickName(resultMypageInfo.getNickName())
                        .email(memberInfoSession.getEmail())
                        .genderCode(resultMypageInfo.getGenderCode())
                        .birthDay(resultMypageInfo.getBirthDay())
                        .memberStatusCode(memberInfoSession.getMemberStatusCode())
                        .signInRequestCnt(memberInfoSession.getSignInRequestCnt())
                        .build();
                request.getSession().setAttribute(Const.MEMBER_INFO_SESSION, memberDTO);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}
