package com.tripmate.controller;


import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.InviteCodeVO;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.NotificationVO;
import com.tripmate.domain.PlanBasicInfoVO;
import com.tripmate.domain.PopularPlanVO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.Const;
import com.tripmate.service.apiservice.MainApiService;
import com.tripmate.service.apiservice.PlanApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/main", produces = "application/json; charset=utf8")
public class MainController {
    private final PlanApiService planApiService;
    private final MainApiService mainApiService;

    @GetMapping("/main")
    public ModelAndView viewMain(HttpServletRequest request) {
        MemberDTO memberInfoSession = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);
        InviteCodeVO inviteCodeSession = (InviteCodeVO) request.getSession().getAttribute(Const.INVITE_CODE_SESSION);
        String inviteMemberIdSession = (String) request.getSession().getAttribute(Const.INVITE_MEMBER_ID_SESSION);

        try {
            List<PopularPlanVO> popularPlanList;
            List<PlanBasicInfoVO> recommendationPlanList;

            if (memberInfoSession != null) {
                int unreadNotificationCnt = planApiService.getUnreadNotificationCnt(String.valueOf(memberInfoSession.getMemberNo()));
                request.setAttribute("unreadNotificationCnt", unreadNotificationCnt);

                popularPlanList = mainApiService.searchPopularPlanList(String.valueOf(memberInfoSession.getMemberNo()));
                recommendationPlanList = mainApiService.searchUserRecommendationPlanList(String.valueOf(memberInfoSession.getMemberNo()));

                List<PlanBasicInfoVO> myPlanLikeList = planApiService.searchMyPlanLikeList(String.valueOf(memberInfoSession.getMemberNo()));
                request.setAttribute("myPlanLikeList", myPlanLikeList);

            } else {
                popularPlanList = mainApiService.searchPopularPlanList();
                recommendationPlanList = mainApiService.searchRecommendationPlanList();
            }
            request.setAttribute("popularPlanList", popularPlanList);
            request.setAttribute("recommendationPlanList", recommendationPlanList);

            if (inviteCodeSession != null && inviteMemberIdSession != null) {
                if (inviteMemberIdSession.equals(memberInfoSession.getMemberId())) {
                    request.setAttribute("inviteCodeInfo", inviteCodeSession);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("main/main");
    }

    @GetMapping("/notificationList")
    public @ResponseBody ModelAndView notificationList(HttpServletRequest request) {
        MemberDTO memberInfoSession = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);

        try {
            List<NotificationVO> notificationList = planApiService.searchNotificationList(String.valueOf(memberInfoSession.getMemberNo()));

            request.setAttribute("notificationList", notificationList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("main/notificationList");
    }

    @PostMapping("/notification")
    public @ResponseBody String updateNotificationReadDateTime(@RequestParam(value = "memberNo") String memberNo,
                                          @RequestParam(value = "notificationNo") String notificationNo) {
        ApiResult result;

        try {
            boolean isUpdateReadDateTime = planApiService.updateNotificationReadDateTime(memberNo, notificationNo);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isUpdateReadDateTime", isUpdateReadDateTime);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}
