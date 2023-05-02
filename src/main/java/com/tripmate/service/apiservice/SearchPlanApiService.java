package com.tripmate.service.apiservice;

import com.tripmate.domain.SearchPlanResultVO;

import java.util.List;

public interface SearchPlanApiService {
    List<SearchPlanResultVO> searchPlanByKeyword(String memberNo, String keyword) throws Exception;

}
