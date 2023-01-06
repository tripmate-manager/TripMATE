package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
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

@Controller
@RequestMapping("/test")
public class TestController {
    private static Logger log = LoggerFactory.getLogger(TestController.class);

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mav;
    }

    @GetMapping("/callApiCodeList")
    public ModelAndView callApiCodeList() {
        ModelAndView mav = new ModelAndView("test");
        mav.addObject("data", CodeUtil.searchCommonDetailCodeList("CT001").toString());
        log.debug(CodeUtil.searchCommonDetailCodeList("CT001").toString());
        return mav;
    }

    @GetMapping("/callApiCode")
    public ModelAndView callApiCode() {
        ModelAndView mav = new ModelAndView("test");
        mav.addObject("data", CodeUtil.getCommonDetailCode("CT001", "10").toString());
        log.debug(CodeUtil.getCommonDetailCode("CT001", "10").toString());
        return mav;
    }
}
