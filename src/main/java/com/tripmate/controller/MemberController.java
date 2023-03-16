package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.MemberMailDTO;
import com.tripmate.domain.MypageDTO;
import com.tripmate.domain.SignInDTO;
import com.tripmate.domain.UpdatePasswordDTO;
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
    public @ResponseBody String signUp(HttpServletRequest request, @Valid MemberDTO memberDTO) {
        ApiResult result;

        try {
            memberDTO.setMemberStatusCode(ConstCode.MEMBER_STATUS_CODE_TEMPORARY);
            memberApiService.signUp(memberDTO);

            if (request.getSession().getAttribute(Const.INVITE_CODE_SESSION) != null) {
                request.getSession().setAttribute(Const.INVITE_MEMBER_ID_SESSION, memberDTO.getMemberId());
            }

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
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
            Boolean isDuplicate = memberApiService.isDuplicate(type, value);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDuplicate", isDuplicate);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        }  catch (Exception e) {
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
            String email = memberApiService.certificationMailConfirm(memberId, key, mailTypeCode);

            if (ConstCode.EMAIL_TYPE_CODE_EMAIL_CHANGE.equals(mailTypeCode)) {
                MemberDTO memberInfoSession = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);
                MemberDTO memberDTO = MemberDTO.builder()
                        .memberNo(memberInfoSession.getMemberNo())
                        .memberId(memberInfoSession.getMemberId())
                        .memberPassword(memberInfoSession.getMemberPassword())
                        .memberName(memberInfoSession.getMemberName())
                        .nickName(memberInfoSession.getNickName())
                        .email(email)
                        .genderCode(memberInfoSession.getGenderCode())
                        .birthDay(memberInfoSession.getBirthDay())
                        .memberStatusCode(memberInfoSession.getMemberStatusCode())
                        .signInRequestCnt(memberInfoSession.getSignInRequestCnt())
                        .build();
                request.getSession().setAttribute(Const.MEMBER_INFO_SESSION, memberDTO);
            }

            return new ModelAndView("members/signIn");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("members/signUp");
    }

    @PostMapping("/signIn")
    public @ResponseBody String signIn(HttpServletRequest request, @Valid SignInDTO signInDTO) {
        ApiResult result;

        try {
            MemberDTO memberDTO = memberApiService.signIn(signInDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();

            if (memberDTO.getSignInRequestCnt() >= Const.SIGNIN_LIMIT_CNT) {
                result.put("signInRequestCnt", memberDTO.getSignInRequestCnt());
                return result.toJson();
            }

            if (ConstCode.MEMBER_STATUS_CODE_COMPLETE.equals(memberDTO.getMemberStatusCode()) || ConstCode.MEMBER_STATUS_CODE_ISSUE_TEMPORARY_PASSWORD.equals(memberDTO.getMemberStatusCode())) {
                request.getSession().setAttribute(Const.MEMBER_INFO_SESSION, memberDTO);
            }

            result.put("memberStatusCode", memberDTO.getMemberStatusCode());
            result.put("email", memberDTO.getEmail());
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
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
            String memberId = memberApiService.findId(memberName, email);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("memberId", memberId.substring(0, memberId.length() - 4) + "****");
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
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
            boolean isSendMailSuccess = memberApiService.isSendMailSuccess(type, memberMailDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("sendMailSuccess", isSendMailSuccess);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/changePassword")
    public @ResponseBody String changePassword(HttpServletRequest request, @Valid UpdatePasswordDTO updatePasswordDTO) {
        ApiResult result;
        MemberDTO memberInfoSession = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);

        try {
            if (memberInfoSession == null) {
                throw new IOException("session is Empty");
            }

            if (updatePasswordDTO.getNewMemberPassword().equals(memberInfoSession.getMemberPassword())) {
                throw new IOException("current session password is the same as new password");
            }

            UpdatePasswordDTO updatePasswordRequestDTO = UpdatePasswordDTO.builder()
                    .memberNo(memberInfoSession.getMemberNo())
                    .memberPassword(updatePasswordDTO.getMemberPassword())
                    .newMemberPassword(updatePasswordDTO.getNewMemberPassword())
                    .build();

            boolean isUpdatePasswordSuccess = memberApiService.updatePassword(updatePasswordRequestDTO);

            MemberDTO memberDTO = MemberDTO.builder()
                    .memberNo(memberInfoSession.getMemberNo())
                    .memberId(memberInfoSession.getMemberId())
                    .memberPassword(updatePasswordDTO.getNewMemberPassword())
                    .memberName(memberInfoSession.getMemberName())
                    .nickName(memberInfoSession.getNickName())
                    .email(memberInfoSession.getEmail())
                    .genderCode(memberInfoSession.getGenderCode())
                    .birthDay(memberInfoSession.getBirthDay())
                    .memberStatusCode(memberInfoSession.getMemberStatusCode())
                    .signInRequestCnt(memberInfoSession.getSignInRequestCnt())
                    .build();
            request.getSession().setAttribute(Const.MEMBER_INFO_SESSION, memberDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isUpdatePasswordSuccess", isUpdatePasswordSuccess);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/signOut")
    public String signOut(HttpServletRequest request) {
        request.getSession().invalidate();

        return "redirect:/main/main.trip";
    }

    @PostMapping("/withdraw")
    public @ResponseBody String withdraw(HttpServletRequest request) {
        ApiResult result;
        MemberDTO sessionDTO = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);

        try {
            if (sessionDTO == null) {
                throw new IOException("session is Empty");
            }
            boolean isWithdrawSuccess = memberApiService.withdraw(sessionDTO.getMemberNo());

            request.getSession().invalidate();

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isWithdrawSuccess", isWithdrawSuccess);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
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

            if (mypageDTO.getMemberNo() != memberInfoSession.getMemberNo()) {
                throw new IOException("mypageDTO's member number is different from the current session's member number");
            }

            MypageDTO requestMypageInfo = MypageDTO.builder()
                    .memberNo(memberInfoSession.getMemberNo())
                    .nickName(mypageDTO.getNickName())
                    .birthDay(mypageDTO.getBirthDay())
                    .genderCode(mypageDTO.getGenderCode()).build();

            MypageDTO mypageResultDTO = memberApiService.editMypageMemberInfo(requestMypageInfo.getMemberNo(), requestMypageInfo);

            MemberDTO memberDTO = MemberDTO.builder()
                    .memberNo(mypageResultDTO.getMemberNo())
                    .memberId(memberInfoSession.getMemberId())
                    .memberPassword(memberInfoSession.getMemberPassword())
                    .memberName(memberInfoSession.getMemberName())
                    .nickName(mypageResultDTO.getNickName())
                    .email(memberInfoSession.getEmail())
                    .genderCode(mypageResultDTO.getGenderCode())
                    .birthDay(mypageResultDTO.getBirthDay())
                    .memberStatusCode(memberInfoSession.getMemberStatusCode())
                    .signInRequestCnt(memberInfoSession.getSignInRequestCnt())
                    .build();
            request.getSession().setAttribute(Const.MEMBER_INFO_SESSION, memberDTO);
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}
