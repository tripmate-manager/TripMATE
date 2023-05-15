package com.tripmate.service.apiservice;

import com.tripmate.domain.PlanBasicInfoVO;
import com.tripmate.domain.PopularPlanVO;

import java.util.List;

public interface MainApiService {
    List<PopularPlanVO> searchPopularPlanList() throws Exception;
    List<PopularPlanVO> searchPopularPlanList(String memberNo) throws Exception;
    List<PlanBasicInfoVO> searchUserRecommendationPlanList(String memberNo) throws Exception;
}
