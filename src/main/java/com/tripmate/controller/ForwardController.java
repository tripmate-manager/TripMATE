package com.tripmate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/forward")
public class ForwardController {
    @GetMapping(value = "/**/*.trip")
    public String forwardJspPage(final HttpServletRequest request) {
        String view = request.getRequestURI();

        if (log.isDebugEnabled()) {
            log.debug("forward jsp page url = " + view);
        }

        return view.substring(9, view.length() - 5);
    }
}