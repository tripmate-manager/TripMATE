package com.tripmate.entity;

import lombok.Builder;
import org.json.JSONObject;

public class ApiResult {
    private final String code;
    private final String message;
    private JSONObject json;

    @Builder
    public ApiResult(String code, String message) {
        json = new JSONObject();
        this.code = code;
        this.message = message;
    }

    public void put(final String key, final Object value) {
        json.put(key, value);
    }

    public String toJson() {
        json.put("code", code);
        json.put("message", message);

        return json.toString();
    }
}
