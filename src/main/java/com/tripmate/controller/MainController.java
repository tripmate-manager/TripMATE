package com.tripmate.controller;


import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.NotificationVO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.Const;
import com.tripmate.service.apiservice.PlanApiService;
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
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/main", produces = "application/json; charset=utf8")
public class MainController {
    private final PlanApiService planApiService;

    @Autowired
    public MainController(PlanApiService planApiService) {
        this.planApiService = planApiService;
    }

    @GetMapping("/main")
    public ModelAndView viewMain(HttpServletRequest request) {
        MemberDTO memberInfoSession = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);

        try {
            if (memberInfoSession != null) {
                int unreadNotificationCnt = planApiService.getUnreadNotificationCnt(String.valueOf(memberInfoSession.getMemberNo()));
                request.setAttribute("unreadNotificationCnt", unreadNotificationCnt);
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
            List<NotificationVO> notificationApiResult = planApiService.searchNotificationList(String.valueOf(memberInfoSession.getMemberNo()));
            List<NotificationVO> notificationList = new ArrayList<>();

            for (NotificationVO notificationApiResultVO : notificationApiResult) {
                NotificationVO notificationVO = NotificationVO.builder()
                        .notificationNo(notificationApiResultVO.getNotificationNo())
                        .planNo(notificationApiResultVO.getPlanNo())
                        .planTitle(notificationApiResultVO.getPlanTitle())
                        .leaderName(notificationApiResultVO.getLeaderName())
                        .postNo(notificationApiResultVO.getPostNo())
                        .postTitle(notificationApiResultVO.getPostTitle())
                        .notificationTypeCode(notificationApiResultVO.getNotificationTypeCode())
                        .senderNo(notificationApiResultVO.getSenderNo())
                        .senderName(notificationApiResultVO.getSenderName())
                        .receiverNo(notificationApiResultVO.getReceiverNo())
                        .readDateTime(notificationApiResultVO.getReadDateTime())
                        .notificationDateTime(notificationDateTimeFormat(notificationApiResultVO.getNotificationDateTime()))
                        .build();

                notificationList.add(notificationVO);
            }

            request.setAttribute("notificationList", notificationList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("main/notificationList");
    }

    private String notificationDateTimeFormat(String dateTime) {
        String result = null;

        LocalDateTime nowDateTime = LocalDateTime.now();

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime inputDateTime = LocalDateTime.parse(dateTime, inputFormatter);

        Duration duration = Duration.between(inputDateTime, nowDateTime);
        long diffDay = duration.getSeconds()/86400;
        long diffHour = (duration.getSeconds()%86400)/3600;
        long diffMinute = (duration.getSeconds() % 3600)/60;

        if (diffDay > 1) {
            result = dateTime.substring(0, 10).replaceAll("-", ".");
        } else if (diffDay > 0) {
            result = "어제";
        } else if (diffHour > 0) {
            result = diffHour + "시간 전";
        } else if (diffHour == 0) {
            result = diffMinute + "분 전";
        }

        return result;
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
