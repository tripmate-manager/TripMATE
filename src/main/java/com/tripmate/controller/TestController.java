package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.CommonDetailCodeVO;
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

import java.io.IOException;
import java.util.List;

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
            log.info(getTest.clone().execute().body().toString());
            mav.addObject("data", getTest.clone().execute().body());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return mav;
    }

    @GetMapping("/callApiCodeList")
    public ModelAndView callApiCodeList() {
        ModelAndView mav = new ModelAndView("test");

        List<CommonDetailCodeVO> codeList = CodeUtil.searchCommonDetailCodeList(ConstCode.POST_TYPE_CODE);
        log.debug(codeList.toString());

        mav.addObject("data", codeList.toString());
        return mav;
    }

    @GetMapping("/callApiCode")
    public ModelAndView callApiCode() {
        ModelAndView mav = new ModelAndView("test");

        CommonDetailCodeVO code = CodeUtil.getCommonDetailCode(ConstCode.POST_TYPE_CODE, ConstCode.POST_TYPE_CODE_LODGING);
        log.debug(code.toString());

        mav.addObject("data", code.toString());
        return mav;
    }
}
