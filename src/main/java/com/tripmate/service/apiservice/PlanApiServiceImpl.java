package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.common.exception.ApiCommonException;
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
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class PlanApiServiceImpl implements PlanApiService {
    @Override
    public List<PlanAttributeVO> searchPlanAttributeList(String attributeTypeCode) throws Exception {
        Call<ResponseWrapper<PlanAttributeVO>> data = RetrofitClient.getApiService(PlanService.class).selectPlanAttributeList(attributeTypeCode);
        List<PlanAttributeVO> result ;

        ResponseWrapper<PlanAttributeVO> response = data.clone().execute().body();

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().get(0) == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData();
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public List<PlanAddressVO> searchAddressList() throws Exception {
        Call<ResponseWrapper<PlanAddressVO>> data = RetrofitClient.getApiService(PlanService.class).selectAddressList();
        List<PlanAddressVO> result;

        ResponseWrapper<PlanAddressVO> response = data.clone().execute().body();

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().get(0) == null) {
                throw new IOException("response's data is Empty");
            }

            result = response.getData();
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public List<PlanAddressVO> searchAddressList(String sidoName) throws Exception {
        Call<ResponseWrapper<PlanAddressVO>> data = RetrofitClient.getApiService(PlanService.class).selectAddressList(sidoName);
        List<PlanAddressVO> result;

        ResponseWrapper<PlanAddressVO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().get(0) == null) {
                throw new IOException("response's data is Empty");
            }

            result = response.getData();
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new ApiCommonException(response.getCode(), response.getMessage());
        }

        return result;
    }

    @Override
    public int createPlan(PlanDTO planDTO) throws Exception {
        Call<ResponseWrapper<Integer>> data = RetrofitClient.getApiService(PlanService.class).createPlan(planDTO);
        int result;

        ResponseWrapper<Integer> response = data.clone().execute().body();

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().size() != 1) {
                throw new IOException("response's data size is not 1");
            }
            if (response.getData().get(0) == null) {
                throw new IOException("response's data is Empty");
            }

            if (response.getData().get(0) == 0) {
                throw new IOException("response's data is not valid");
            }

            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new ApiCommonException(response.getCode(), response.getMessage());
        }

        return result;
    }

    @Override
    public List<PlanVO> searchMemberPlanList(String memberNo) throws Exception {
        Call<ResponseWrapper<PlanVO>> data = RetrofitClient.getApiService(PlanService.class).searchMemberPlanList(memberNo);
        List<PlanVO> result;

        ResponseWrapper<PlanVO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData();
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public PlanVO getPlanInfo(String planNo, String memberNo) throws Exception {
        Call<ResponseWrapper<PlanVO>> data = RetrofitClient.getApiService(PlanService.class).getPlanInfo(planNo, memberNo);
        PlanVO result;

        ResponseWrapper<PlanVO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null || response.getData().get(0) == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public List<PlanMateVO> searchPlanMateList(String planNo) throws Exception {
        Call<ResponseWrapper<PlanMateVO>> data = RetrofitClient.getApiService(PlanService.class).searchPlanMateList(planNo);
        List<PlanMateVO> result;

        ResponseWrapper<PlanMateVO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData();
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public boolean updatePlan(String planNo, PlanDTO planDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(PlanService.class).updatePlan(planNo, planDTO);
        boolean result;

        ResponseWrapper<Boolean> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public List<PlanMateVO> searchMemberList(String searchDiviCode, String searchKeyword) throws Exception {
        Call<ResponseWrapper<PlanMateVO>> data = RetrofitClient.getApiService(PlanService.class).searchMemberList(searchDiviCode, searchKeyword);
        List<PlanMateVO> result;

        ResponseWrapper<PlanMateVO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }

            result = response.getData();
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new ApiCommonException(response.getCode(), response.getMessage());
        }

        return result;
    }

    @Override
    public InviteCodeVO createInviteAuthCode(String planNo, String memberNo, String inviteTypeCode) throws Exception {
        Call<ResponseWrapper<InviteCodeVO>> data = RetrofitClient.getApiService(PlanService.class).createInviteAuthCode(planNo, memberNo, inviteTypeCode);
        InviteCodeVO result;

        ResponseWrapper<InviteCodeVO> response = data.clone().execute().body();

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().size() != 1) {
                throw new IOException("response's data size is not 1");
            }
            if (response.getData().get(0) == null) {
                throw new IOException("response's data is Empty");
            }

            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new ApiCommonException(response.getCode(), response.getMessage());
        }

        return result;
    }

    @Override
    public boolean createNotification(NotificationDTO notificationDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(PlanService.class).createNotification(notificationDTO);
        boolean result;

        ResponseWrapper<Boolean> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public List<NotificationVO> searchNotificationList(String memberNo) throws Exception {
        Call<ResponseWrapper<NotificationVO>> data = RetrofitClient.getApiService(PlanService.class).searchNotificationList(memberNo);
        List<NotificationVO> result;

        ResponseWrapper<NotificationVO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData();
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public int getUnreadNotificationCnt(String memberNo) throws Exception {
        Call<ResponseWrapper<Integer>> data = RetrofitClient.getApiService(PlanService.class).getUnreadNotificationCnt(memberNo);
        int result;

        ResponseWrapper<Integer> response = data.clone().execute().body();

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().size() != 1) {
                throw new IOException("response's data size is not 1");
            }
            if (response.getData().get(0) == null) {
                throw new IOException("response's data is Empty");
            }

            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new ApiCommonException(response.getCode(), response.getMessage());
        }

        return result;
    }

    @Override
    public boolean updateNotificationReadDateTime(String memberNo, String notificationNo) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(PlanService.class).updateNotificationReadDateTime(memberNo, notificationNo);
        boolean result;

        ResponseWrapper<Boolean> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public boolean exitPlan(ExitPlanDTO exitPlanDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(PlanService.class).exitPlan(exitPlanDTO);
        boolean result;

        ResponseWrapper<Boolean> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public InviteCodeVO getInviteCodeInfo(String inviteCodeNo) throws Exception {
        Call<ResponseWrapper<InviteCodeVO>> data = RetrofitClient.getApiService(PlanService.class).getInviteCodeInfo(inviteCodeNo);
        InviteCodeVO result;

        ResponseWrapper<InviteCodeVO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public boolean insertPlanMate(PlanMateDTO planMateDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(PlanService.class).insertPlanMate(planMateDTO);
        boolean result;

        ResponseWrapper<Boolean> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public boolean insertPlanLike(String planNo, String memberNo) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(PlanService.class).insertPlanLike(planNo, memberNo);
        boolean result;

        ResponseWrapper<Boolean> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public boolean deletePlanLike(String planNo, String memberNo) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(PlanService.class).deletePlanLike(planNo, memberNo);
        boolean result;

        ResponseWrapper<Boolean> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public List<PlanBasicInfoVO> searchMyPlanLikeList(String memberNo) throws Exception {
        Call<ResponseWrapper<PlanBasicInfoVO>> data = RetrofitClient.getApiService(PlanService.class).searchMyPlanLikeList(memberNo);
        List<PlanBasicInfoVO> result;

        ResponseWrapper<PlanBasicInfoVO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null) {
                throw new IOException("response's data is Empty");
            }
            result = response.getData();
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new IOException(response.getMessage());
        }

        return result;
    }
}