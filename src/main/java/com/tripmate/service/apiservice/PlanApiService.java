package com.tripmate.service.apiservice;

import com.tripmate.domain.CreatePlanDTO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanVO;

import java.util.List;

public interface PlanApiService {
    List<PlanAttributeVO> searchPlanAttributeList(String attributeTypeCode) throws Exception;
    List<PlanAddressVO> searchAddressList() throws Exception;
    List<PlanAddressVO> searchAddressList(String sidoName) throws Exception;
    boolean createPlan(CreatePlanDTO createPlanDTO) throws Exception;
    List<PlanVO> searchMemberPlanList(String memberNo) throws Exception;
}