package com.tripmate.controller;

import com.tripmate.domain.CreatePlanDTO;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanVO;
import com.tripmate.domain.ResponseWrapper;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.HashSet;
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
            ResponseWrapper<PlanAttributeVO> planAttributeResponse = planApiService.selectPlanAttributeList(ConstCode.ATTRIBUTE_TYPE_CODE_TRIP_THEME);
            ResponseWrapper<PlanAddressVO> planAddressResponse = planApiService.selectAddressList();

            if (planAttributeResponse == null || planAddressResponse == null) {
                throw new IOException("api response data is empty");
            }

            if (ApiResultEnum.SUCCESS.getCode().equals(planAttributeResponse.getCode())
                    && ApiResultEnum.SUCCESS.getCode().equals(planAddressResponse.getCode())) {
                if (planAttributeResponse.getData().get(0) == null || planAddressResponse.getData().get(0) == null) {
                    throw new IOException("response's data is Empty");
                }

                Set<String> sidoNameList = new HashSet<>();
                for (PlanAddressVO planAddressVO : planAddressResponse.getData()) {
                    sidoNameList.add(planAddressVO.getSidoName());
                }

                request.setAttribute("planThemeList", planAttributeResponse.getData());
                request.setAttribute("sidoNameList", sidoNameList);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("plans/createPlan");
    }

    @GetMapping("/addressOption/{sidoName}")
    public @ResponseBody String getAddressOptionList(@PathVariable(value = "sidoName") @NotBlank String sidoName) {
        ApiResult result;

        try {
            ResponseWrapper<PlanAddressVO> response = planApiService.selectAddressList(sidoName);

            if (response == null) {
                throw new IOException("response is Empty");
            }

            result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();
            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().get(0) == null) {
                    throw new IOException("response's data is Empty");
                }

                result.put("addressOptionList", response.getData());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/createPlan")
    public @ResponseBody String createPlan(@Valid CreatePlanDTO createPlanDTO) {
        ApiResult result;

        try {
            ResponseWrapper<Boolean> response = planApiService.createPlan(createPlanDTO);

            if (response == null) {
                throw new IOException("api response data is empty");
            }

            result = ApiResult.builder().code(response.getCode()).message(response.getMessage()).build();

            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData().size() != 1) {
                    throw new IOException("response's data size is not 1");
                }
                if (response.getData().get(0) == null) {
                    throw new IOException("response's data is Empty");
                }

                result.put("createPlanSuccess", response.getData().get(0));
            }
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
            ResponseWrapper<PlanVO> response = planApiService.searchMemberPlanList(String.valueOf(memberInfoSession.getMemberNo()));

            if (response == null) {
                throw new IOException("response is Empty");
            }

            if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
                if (response.getData() == null) {
                    throw new IOException("response's data is Empty");
                }

                request.setAttribute("planList", response.getData());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("plans/myPlan");
    }
}
