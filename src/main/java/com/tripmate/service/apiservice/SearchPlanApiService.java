package com.tripmate.service.apiservice;

import com.tripmate.domain.PlanBasicInfoVO;
import com.tripmate.domain.SearchAttributeDTO;

import java.util.List;

public interface SearchPlanApiService {
    List<PlanBasicInfoVO> searchPlanByKeyword(String memberNo, String keyword) throws Exception;
    List<PlanBasicInfoVO> searchPlanByAttribute(SearchAttributeDTO searchAttributeDTO) throws Exception;
}
