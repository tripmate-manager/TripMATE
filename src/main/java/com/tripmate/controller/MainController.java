package com.tripmate.controller;


import com.tripmate.domain.MemberDTO;
import com.tripmate.entity.Const;
import com.tripmate.service.apiservice.PlanApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
}
