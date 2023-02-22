package com.tripmate.service.apiservice;

import com.tripmate.domain.CreatePlanDTO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanVO;
import com.tripmate.domain.ResponseWrapper;

public interface PlanApiService {
    ResponseWrapper<PlanAttributeVO> selectPlanAttributeList(String attributeTypeCode);
    ResponseWrapper<PlanAddressVO> selectAddressList();
    ResponseWrapper<PlanAddressVO> selectAddressList(String sidoName);
    ResponseWrapper<Boolean> createPlan(CreatePlanDTO createPlanDTO);
    ResponseWrapper<PlanVO> searchMemberPlanList(String memberNo);
}