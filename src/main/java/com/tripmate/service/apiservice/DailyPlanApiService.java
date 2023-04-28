package com.tripmate.service.apiservice;

import com.tripmate.domain.DailyPlanCntVO;
import com.tripmate.domain.DailyPlanDTO;
import com.tripmate.domain.DailyPlanVO;
import com.tripmate.domain.DeleteDailyPlanDTO;
import com.tripmate.domain.NotificationDTO;

import java.util.List;

public interface DailyPlanApiService {
    boolean insertDailyPlan(DailyPlanDTO dailyPlanDTO) throws Exception;
    List<String> deleteDailyPlan(DeleteDailyPlanDTO deleteDailyPlanDTO) throws Exception;
    List<DailyPlanCntVO> searchDailyPlanCntByDay(String planNo) throws Exception;
    DailyPlanVO searchDailyPlanListByDay(String planNo, String memberNo, String dayGroup) throws Exception;
    boolean deleteDailyPlanNotification(String dailyPlanNo, String memberNo) throws Exception;
    boolean updateDailyPlanNotification(NotificationDTO notificationDTO) throws Exception;
}