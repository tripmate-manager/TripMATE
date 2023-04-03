package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.DailyPlanDTO;
import com.tripmate.domain.DeleteDailyPlanDTO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.apiservice.DailyPlanApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
@Controller
@RequestMapping(value = "/dailyPlans", produces = "application/json; charset=utf8")
public class DailyPlanController {
    private final DailyPlanApiService dailyPlanApiService;

    @Autowired
    public DailyPlanController(DailyPlanApiService dailyPlanApiService) {
        this.dailyPlanApiService = dailyPlanApiService;
    }

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
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeleteDailyPlanSuccess", dailyPlanApiService.deleteDailyPlan(deleteDailyPlanDTO));
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
                               @RequestParam(value = "dayGroup") @NotBlank String dayGroup) {
        try {
            request.setAttribute("dailyPlanList", dailyPlanApiService.searchDailyPlanListByDay(planNo, dayGroup));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ModelAndView("dailyplans/dailyPlan");
    }
}