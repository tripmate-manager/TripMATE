package com.tripmate.service.apiservice;

import com.tripmate.domain.DailyPlanCntVO;
import com.tripmate.domain.DailyPlanDTO;
import com.tripmate.domain.DailyPlanVO;
import com.tripmate.domain.DeleteDailyPlanDTO;

import java.util.List;

public interface DailyPlanApiService {
    boolean insertDailyPlan(DailyPlanDTO dailyPlanDTO) throws Exception;
    boolean deleteDailyPlan(DeleteDailyPlanDTO deleteDailyPlanDTO) throws Exception;
    List<DailyPlanCntVO> searchDailyPlanCntByDay(String planNo) throws Exception;
    List<DailyPlanVO> searchDailyPlanListByDay(String planNo, String dayGroup) throws Exception;
}