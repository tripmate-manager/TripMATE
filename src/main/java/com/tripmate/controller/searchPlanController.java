package com.tripmate.controller;


import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.SearchAttributeDTO;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.apiservice.PlanApiService;
import com.tripmate.service.apiservice.SearchPlanApiService;
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
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping(value = "/searchPlan", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class searchPlanController {
    private final SearchPlanApiService searchPlanApiService;
    private final PlanApiService planApiService;

    @GetMapping("/search")
    public ModelAndView viewSearchPlan(HttpServletRequest request) {
        try {
            List<PlanAttributeVO> planAttributeVOList = planApiService.searchPlanAttributeList(ConstCode.ATTRIBUTE_TYPE_CODE_TRIP_THEME);
            List<PlanAddressVO> planAddressVOList = planApiService.searchAddressList();

            Set<String> sidoNameList = new HashSet<>();
            for (PlanAddressVO planAddressVO : planAddressVOList) {
                sidoNameList.add(planAddressVO.getSidoName());
            }

            request.setAttribute("planThemeList", planAttributeVOList);
            request.setAttribute("sidoNameList", sidoNameList);
            request.setAttribute("popularSearchKeywordList", searchPlanApiService.searchPopularSearchKeyword());
            request.setAttribute("popularHashtagList", searchPlanApiService.searchPopularHashtag());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("searchplan/searchPlan");
    }

    @PostMapping("/keyword")
    public @ResponseBody ModelAndView searchPlanByKeyword(HttpServletRequest request,
                                                          @RequestParam(value = "memberNo") String memberNo,
                                                          @RequestParam(value = "keyword") String keyword) {
        try {
            request.setAttribute("searchPlanResultList", searchPlanApiService.searchPlanByKeyword(memberNo, keyword));
            request.setAttribute("keyword", keyword);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("searchplan/searchPlanResult");
    }

    @PostMapping("/attribute")
    public @ResponseBody ModelAndView searchPlanByAttribute(HttpServletRequest request, @Valid SearchAttributeDTO searchAttributeDTO) {
        try {
            request.setAttribute("searchPlanResultList", searchPlanApiService.searchPlanByAttribute(searchAttributeDTO));
            request.setAttribute("searchAttributeDTO", searchAttributeDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("searchplan/searchPlanResult");
    }
}
