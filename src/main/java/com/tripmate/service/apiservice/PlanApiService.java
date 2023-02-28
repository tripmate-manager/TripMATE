package com.tripmate.service.apiservice;

import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanDTO;
import com.tripmate.domain.PlanMateVO;
import com.tripmate.domain.PlanVO;

import java.util.List;

public interface PlanApiService {
    List<PlanAttributeVO> searchPlanAttributeList(String attributeTypeCode) throws Exception;
    List<PlanAddressVO> searchAddressList() throws Exception;
    List<PlanAddressVO> searchAddressList(String sidoName) throws Exception;
    boolean createPlan(PlanDTO planDTO) throws Exception;
    List<PlanVO> searchMemberPlanList(String memberNo) throws Exception;
    PlanVO getPlanInfo(String memberNo) throws Exception;
    List<PlanMateVO> searchPlanMateList(String planNo) throws Exception;
    boolean updatePlan(String planNo, PlanDTO planDTO) throws Exception;
}