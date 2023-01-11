package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.CommonDetailCodeVO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.CodeUtil;
import com.tripmate.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Call;

@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/page")
    public String testPage() {
        return "test";
    }

    @GetMapping("/sessionTest")
    public String sessionTest() {
        return "sessionTest";
    }

    @GetMapping("/callApi")
    public ModelAndView testCallApi() {
        ModelAndView mav = new ModelAndView("test");

        Call<Object> getTest = RetrofitClient.getApiService(TestService.class).getTest();

        try {
            Object body = getTest.clone().execute().body();
            body = body == null ? "body is null" : body;

            log.info(body.toString());
            mav.addObject("data", body);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return mav;
    }

    @GetMapping("/callApiCodeList")
    public ModelAndView callApiCodeList() {
        ModelAndView mav = new ModelAndView("test");

        ResponseWrapper<CommonDetailCodeVO> codeListResponse = CodeUtil.searchCommonDetailCodeList(ConstCode.POST_TYPE_CODE);
        log.debug(codeListResponse.toString());

        StringBuilder sb = new StringBuilder();

        if (ApiResultEnum.SUCCESS.getCode().equals(codeListResponse.getCode())) {
            codeListResponse.getData().forEach(commonDetailCodeVO -> sb.append(commonDetailCodeVO).append("\n"));
        } else {
            sb.append(codeListResponse.getCode())
              .append("\n")
              .append(codeListResponse.getMessage());
        }

        return mav.addObject("data", sb);
    }

    @GetMapping("/callApiCode")
    public ModelAndView callApiCode() {
        ModelAndView mav = new ModelAndView("test");

        ResponseWrapper<CommonDetailCodeVO> codeResponse = CodeUtil.getCommonDetailCode(ConstCode.POST_TYPE_CODE, ConstCode.POST_TYPE_CODE_LODGING);
        log.debug(codeResponse.toString());

        StringBuilder sb = new StringBuilder();

        if (ApiResultEnum.SUCCESS.getCode().equals(codeResponse.getCode())) {
            codeResponse.getData().forEach(commonDetailCodeVO -> sb.append(commonDetailCodeVO).append("\n"));
        } else {
            sb.append(codeResponse.getCode())
              .append("\n")
              .append(codeResponse.getMessage());
        }

        return mav.addObject("data", sb);
    }
}
