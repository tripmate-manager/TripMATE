package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.common.util.FileUploadUtil;
import com.tripmate.domain.DailyPlanDTO;
import com.tripmate.domain.DeleteDailyPlanDTO;
import com.tripmate.domain.NotificationDTO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.apiservice.DailyPlanApiService;
import com.tripmate.service.apiservice.PlanApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/dailyPlans", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class DailyPlanController {
    private final DailyPlanApiService dailyPlanApiService;
    private final PlanApiService planApiService;
    private final FileUploadUtil fileUploadUtil;

    @PostMapping("/addDailyPlan")
    public @ResponseBody String addDailyPlan(@Valid DailyPlanDTO dailyPlanDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isInsertDailyPlanSuccess", dailyPlanApiService.insertDailyPlan(dailyPlanDTO));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/deleteDailyPlan")
    public @ResponseBody String deleteDailyPlan(@Valid DeleteDailyPlanDTO deleteDailyPlanDTO) {
        ApiResult result;

        try {
            List<String> reviewImageNameList = dailyPlanApiService.deleteDailyPlan(deleteDailyPlanDTO);
            fileUploadUtil.deleteFile(reviewImageNameList);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeleteDailyPlanSuccess", true);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/dailyPlan")
    public @ResponseBody ModelAndView viewDailyPlan(HttpServletRequest request,
                               @RequestParam(value = "planNo") @NotBlank String planNo,
                               @RequestParam(value = "memberNo") @NotBlank String memberNo,
                               @RequestParam(value = "dayGroup") @NotBlank String dayGroup) {
        try {
            request.setAttribute("dayGroup", dayGroup);
            request.setAttribute("dailyPlanVO", dailyPlanApiService.searchDailyPlanListByDay(planNo, memberNo, dayGroup));
            request.setAttribute("isPlanMate", planApiService.searchPlanMateList(planNo).stream()
                    .anyMatch(mate -> String.valueOf(mate.getMemberNo()).equals(memberNo)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ModelAndView("dailyplans/dailyPlan");
    }

    @PostMapping("/deleteDailyPlanNotification")
    public @ResponseBody String deleteDailyPlanNotification(@RequestParam(value = "dailyPlanNo") @NotBlank String dailyPlanNo,
                                                            @RequestParam(value = "memberNo") @NotBlank String memberNo) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeleteNotificationSuccess", dailyPlanApiService.deleteDailyPlanNotification(dailyPlanNo, memberNo));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/updateDailyPlanNotification")
    public @ResponseBody String updateDailyPlanNotification(@Valid NotificationDTO notificationDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isUpdateNotificationSuccess", dailyPlanApiService.updateDailyPlanNotification(notificationDTO));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}