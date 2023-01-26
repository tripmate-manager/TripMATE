package com.tripmate.entity;

import com.tripmate.common.properties.PropertiesManager;
import org.springframework.util.StringUtils;

public interface Const {
    String DOMAIN               = Const.getProperty("service.domain", ""); // 추후 defaultValue 지정 필요
    String CONTEXT_PATH         = Const.getProperty("service.context", "/");
    String SERVICE_URL          = "http://" + DOMAIN + CONTEXT_PATH;
    String STATIC_CSS_PATH      = SERVICE_URL + "/resources/css";
    String STATIC_IMG_PATH      = SERVICE_URL + "/resources/images";
    String STATIC_JS_PATH       = SERVICE_URL + "/resources/js";

    String API_DOMAIN           = Const.getProperty("tripmate-api.url", ""); // 추후 defaultValue 지정 필요
    String API_CONTEXT_PATH     = Const.getProperty("tripmate-api.context", "/api/");
    String API_URL              = "http://" + API_DOMAIN + API_CONTEXT_PATH;

    String MEMBER_INFO_SESSION  = "memberInfo";

    static String getProperty(final String key) {
        return getProperty(key, "");
    }

    static String getProperty(final String key, final String defaultValue) {
        String value = PropertiesManager.getProperty(key);
        if (StringUtils.isEmpty(key)) {
            value = defaultValue;
        }
        return value;
    }

    String RESULT_CODE_SUCCESS = "0000";
    String RESULT_CODE_MESSAGING = "8888";
    String RESULT_CODE_VALIDATION = "9000";
    String RESULT_CODE_WRONG_PARAMETER = "9001";
    String RESULT_CODE_UNKNOWN = "9999";

    int SIGNIN_LIMIT_CNT        = 5;
}