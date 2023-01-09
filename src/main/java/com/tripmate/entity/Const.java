package com.tripmate.entity;

import com.tripmate.common.properties.PropertiesManager;
import org.springframework.util.StringUtils;

public class Const {
    public static final String DOMAIN               = Const.getProperty("service.domain", ""); // 추후 defaultValue 지정 필요
    public static final String CONTEXT_PATH         = Const.getProperty("service.context", "/");
    public static final String SERVICE_URL          = "http://" + DOMAIN + CONTEXT_PATH;

    public static final String API_DOMAIN           = Const.getProperty("tripmate-api.url", ""); // 추후 defaultValue 지정 필요
    public static final String API_CONTEXT_PATH     = Const.getProperty("tripmate-api.context", "/api/");
    public static final String API_URL              = "http://" + API_DOMAIN + API_CONTEXT_PATH;

    public static String getProperty(final String key) {
        return getProperty(key, "");
    }

    public static String getProperty(final String key, final String defaultValue) {
        String value = PropertiesManager.getProperty(key);
        if (StringUtils.isEmpty(key)) {
            value = defaultValue;
        }
        return value;
    }
}