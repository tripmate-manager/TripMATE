package com.tripmate.service.apiservice;

import com.tripmate.domain.ExitPlanDTO;
import com.tripmate.domain.InviteCodeVO;
import com.tripmate.domain.NotificationDTO;
import com.tripmate.domain.NotificationVO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanBasicInfoVO;
import com.tripmate.domain.PlanDTO;
import com.tripmate.domain.PlanMateDTO;
import com.tripmate.domain.PlanMateVO;
import com.tripmate.domain.PlanVO;

import java.util.List;

public interface PlanApiService {
    List<PlanAttributeVO> searchPlanAttributeList(String attributeTypeCode) throws Exception;
    List<PlanAddressVO> searchAddressList() throws Exception;
    List<PlanAddressVO> searchAddressList(String sidoName) throws Exception;
    int createPlan(PlanDTO planDTO) throws Exception;
    List<PlanVO> searchMemberPlanList(String memberNo) throws Exception;
    PlanVO getPlanInfo(String planNo, String memberNo) throws Exception;
    List<PlanMateVO> searchPlanMateList(String planNo) throws Exception;
    boolean updatePlan(String planNo, PlanDTO planDTO) throws Exception;
    List<PlanMateVO> searchMemberList(String searchDiviCode, String searchKeyword) throws Exception;
    InviteCodeVO createInviteAuthCode(String planNo, String memberNo, String inviteTypeCode) throws Exception;
    boolean createNotification(NotificationDTO notificationDTO) throws Exception;
    List<NotificationVO> searchNotificationList(String memberNo) throws Exception;
    int getUnreadNotificationCnt(String memberNo) throws Exception;
    boolean updateNotificationReadDateTime(String memberNo, String notificationNo) throws Exception;
    boolean exitPlan(ExitPlanDTO exitPlanDTO) throws Exception;
    InviteCodeVO getInviteCodeInfo(String inviteCodeNo) throws Exception;
    boolean insertPlanMate(PlanMateDTO planMateDTO) throws Exception;
    boolean insertPlanLike(String planNo, String memberNo) throws Exception;
    boolean deletePlanLike(String planNo, String memberNo) throws Exception;
    List<PlanBasicInfoVO> searchMyPlanLikeList(String memberNo) throws Exception;
}