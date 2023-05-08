package com.tripmate.service.apiservice;

import com.tripmate.domain.SearchAttributeDTO;
import com.tripmate.domain.SearchPlanResultVO;

import java.util.List;

public interface SearchPlanApiService {
    List<SearchPlanResultVO> searchPlanByKeyword(String memberNo, String keyword) throws Exception;
    List<SearchPlanResultVO> searchPlanByAttribute(SearchAttributeDTO searchAttributeDTO) throws Exception;
}
