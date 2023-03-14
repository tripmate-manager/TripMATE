package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.ExitPlanDTO;
import com.tripmate.domain.InviteCodeVO;
import com.tripmate.domain.NotificationDTO;
import com.tripmate.domain.PlanDTO;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanMateDTO;
import com.tripmate.domain.PlanMateVO;
import com.tripmate.domain.PlanVO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.Const;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.apiservice.PlanApiService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping(value = "/plans", produces = "application/json; charset=utf8")
public class PlanController {
    private final PlanApiService planApiService;

    @Autowired
    public PlanController(PlanApiService planApiService) {
        this.planApiService = planApiService;
    }

    @GetMapping("/createPlan")
    public ModelAndView viewCreatePlan(HttpServletRequest request) {
        try {
            List<PlanAttributeVO> planAttributeVOList = planApiService.searchPlanAttributeList(ConstCode.ATTRIBUTE_TYPE_CODE_TRIP_THEME);
            List<PlanAddressVO> planAddressVOList = planApiService.searchAddressList();

            Set<String> sidoNameList = new HashSet<>();
            for (PlanAddressVO planAddressVO : planAddressVOList) {
                sidoNameList.add(planAddressVO.getSidoName());
            }

            request.setAttribute("planThemeList", planAttributeVOList);
            request.setAttribute("sidoNameList", sidoNameList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("plans/createPlan");
    }

    @GetMapping("/addressOption/{sidoName}")
    public @ResponseBody String getAddressOptionList(@PathVariable(value = "sidoName") @NotBlank String sidoName) {
        ApiResult result;

        try {
            List<PlanAddressVO> planAddressVOList = planApiService.searchAddressList(sidoName);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("addressOptionList", planAddressVOList);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/createPlan")
    public @ResponseBody String createPlan(@Valid PlanDTO planDTO) {
        ApiResult result;

        try {
            int createPlanNo = planApiService.createPlan(planDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("createPlanNo", createPlanNo);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @GetMapping("/myPlan")
    public @ResponseBody ModelAndView myPlan(HttpServletRequest request) {
        MemberDTO memberInfoSession = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);

        try {
            List<PlanVO> planVOList = planApiService.searchMemberPlanList(String.valueOf(memberInfoSession.getMemberNo()));

            request.setAttribute("planList", planVOList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("plans/myPlan");
    }

    @PostMapping("/planMain")
    public ModelAndView viewPlanMain(HttpServletRequest request, @RequestParam(value = "planNo") String planNo) {
        try {
            PlanVO planVO = planApiService.getPlanInfo(planNo);
            List<PlanMateVO> planMateList = planApiService.searchPlanMateList(planNo);

            request.setAttribute("planVO", planVO);
            request.setAttribute("planMateList", planMateList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("plans/planMain");
    }

    @PostMapping("/editPlan")
    public ModelAndView viewEditPlan(HttpServletRequest request, @RequestParam(value = "planNo") String planNo) {
        try {
            List<PlanAttributeVO> planAttributeVOList = planApiService.searchPlanAttributeList(ConstCode.ATTRIBUTE_TYPE_CODE_TRIP_THEME);
            List<PlanAddressVO> planAddressVOList = planApiService.searchAddressList();
            PlanVO planVO = planApiService.getPlanInfo(planNo);

            Set<String> sidoNameList = new HashSet<>();
            for (PlanAddressVO planAddressVO : planAddressVOList) {
                sidoNameList.add(planAddressVO.getSidoName());
            }

            request.setAttribute("planThemeList", planAttributeVOList);
            request.setAttribute("sidoNameList", sidoNameList);
            request.setAttribute("planVO", planVO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("plans/editPlan");
    }

    @PostMapping("/editPlan/callApi")
    public @ResponseBody String editPlan(@Valid PlanDTO planDTO) {
        ApiResult result;

        try {
            boolean isUpdatePlanSuccess = planApiService.updatePlan(String.valueOf(planDTO.getPlanNo()), planDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isUpdatePlanSuccess", isUpdatePlanSuccess);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/searchMember")
    public @ResponseBody String searchMember(@RequestParam(value = "searchDiviCode") String searchDiviCode, @RequestParam(value = "searchKeyword") String searchKeyword) {
        ApiResult result;

        try {
            List<PlanMateVO> memberSearchResultList = planApiService.searchMemberList(searchDiviCode, searchKeyword);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("memberSearchResultList", memberSearchResultList);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/createInviteCode")
    public @ResponseBody String createInviteCode (@RequestParam(value = "planNo") String planNo, @RequestParam(value = "inviteTypeCode") String inviteTypeCode) {
        ApiResult result;

        try {
            InviteCodeVO inviteCodeVO = planApiService.createInviteAuthCode(planNo, inviteTypeCode);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("inviteCodeNo", inviteCodeVO.getInviteCodeNo());
            result.put("inviteCode", inviteCodeVO.getInviteCode());
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @GetMapping("/planMate")
    public @ResponseBody String searchPlanMateList(@RequestParam(value = "planNo") @NotBlank String planNo) {
        ApiResult result;

        try {
            List<PlanMateVO> planMateList = planApiService.searchPlanMateList(planNo);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("planMateList", planMateList);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/exitPlan")
    public @ResponseBody String exitPlan(@Valid ExitPlanDTO exitPlanDTO) {
        ApiResult result;

        try {
            boolean isExitPlanMate = planApiService.exitPlan(exitPlanDTO);
            
            if (exitPlanDTO.getMateNo() != null) {
                NotificationDTO notificationDTO = NotificationDTO.builder()
                        .planNo(exitPlanDTO.getPlanNo())
                        .notificationTypeCode(ConstCode.NOTIFICATION_TYPE_CODE_CHANGE_LEADER)
                        .senderNo(exitPlanDTO.getMemberNo())
                        .receiverNoList(Collections.singletonList(exitPlanDTO.getMateNo()))
                        .build();

                planApiService.createNotification(notificationDTO);
            }

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isExitPlanMate", isExitPlanMate);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/createNotification")
    public @ResponseBody String createNotification(@Valid NotificationDTO notificationDTO) {
        ApiResult result;

        try {
            boolean isCreateNotification = planApiService.createNotification(notificationDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isCreateNotification", isCreateNotification);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @GetMapping("/nonmemberInvitation")
    public @ResponseBody ModelAndView nonmemberInvitation(HttpServletRequest request,
                                                   @RequestParam(value = "inviteCodeNo") @NotBlank String inviteCodeNo) {
        try {
            InviteCodeVO inviteCodeVO = planApiService.getInviteCodeInfo(inviteCodeNo);

            request.getSession().setAttribute(Const.INVITE_CODE_SESSION, inviteCodeVO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("members/signUp");
    }

    @PostMapping("/removeInviteCodeSession")
    public @ResponseBody String removeInviteCodeSession(HttpServletRequest request) {
        //todo: 수정
        JSONObject result = new JSONObject();

        if (request.getSession().getAttribute(Const.INVITE_CODE_SESSION) != null) {
            request.getSession().removeAttribute(Const.INVITE_CODE_SESSION);
        }

        if (request.getSession().getAttribute(Const.INVITE_MEMBER_ID_SESSION) != null) {
            request.getSession().removeAttribute(Const.INVITE_MEMBER_ID_SESSION);
        }

        return result.put("isRemoveInviteCodeSession", true).toString();
    }

    @PostMapping("/insertPlanMate")
    public @ResponseBody String insertPlanMate(@Valid PlanMateDTO planMateDTO) {
        ApiResult result;

        try {
            boolean isInsertPlanMate = planApiService.insertPlanMate(planMateDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isInsertPlanMate", isInsertPlanMate);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}
