package com.tripmate.service.apiservice;

import com.tripmate.domain.DailyPlanDTO;

public interface DailyPlanApiService {
    boolean insertDailyPlan(DailyPlanDTO dailyPlanDTO) throws Exception;
}