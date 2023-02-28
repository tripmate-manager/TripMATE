package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.PlanDTO;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanMateVO;
import com.tripmate.domain.PlanVO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.Const;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.apiservice.PlanApiService;
import lombok.extern.slf4j.Slf4j;
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
            boolean isCreatePlanSuccess = planApiService.createPlan(planDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("createPlanSuccess", isCreatePlanSuccess);
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
}
