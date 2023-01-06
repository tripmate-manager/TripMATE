package com.tripmate.controller;

import com.tripmate.client.RetrofitClient;
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

        Call<Object> getTest = RetrofitClient.getApiService().getTest();

        try {
            Object body = getTest.clone().execute().body();

            if (body == null) {
                body = "Retrofit client's body is null";
            }

            log.info(body.toString());
            mav.addObject("data", body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mav;
    }
}
