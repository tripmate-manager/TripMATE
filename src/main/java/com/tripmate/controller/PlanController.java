package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.CreatePlanDTO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Call;

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

    @PostMapping("/createPlan")
    public ModelAndView viewCreatePlan(HttpServletRequest request) {
        try {
            Call<ResponseWrapper<PlanAttributeVO>> planAttributeData = RetrofitClient.getApiService(PlanService.class).selectPlanAttributeList(ConstCode.ATTRIBUTE_TYPE_CODE_TRIP_THEME);
            ResponseWrapper<PlanAttributeVO> planAttributeResponse = planAttributeData.clone().execute().body();

            Call<ResponseWrapper<PlanAddressVO>> planAddressData = RetrofitClient.getApiService(PlanService.class).selectAddressList();
            ResponseWrapper<PlanAddressVO> planAddressResponse = planAddressData.clone().execute().body();

            if (planAttributeResponse == null || planAddressResponse == null) {
                throw new IOException("api response data is empty");
            } else {
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
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("plans/createPlan");
    }

    @GetMapping("/addressOption/{sidoName}")
    public @ResponseBody String findId(@PathVariable(value = "sidoName") @NotBlank String sidoName) {
        ApiResult result;

        try {
            Call<ResponseWrapper<PlanAddressVO>> data = RetrofitClient.getApiService(PlanService.class).selectAddressList(sidoName);
            ResponseWrapper<PlanAddressVO> response = data.clone().execute().body();

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

    @PostMapping
    public @ResponseBody String createPlan(@Valid CreatePlanDTO createPlanDTO) {
        ApiResult result;

        try {
            Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(PlanService.class).createPlan(createPlanDTO);
            ResponseWrapper<Boolean> response = data.clone().execute().body();

            if (response == null) {
                throw new IOException("api response data is empty");
            } else {
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
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}
