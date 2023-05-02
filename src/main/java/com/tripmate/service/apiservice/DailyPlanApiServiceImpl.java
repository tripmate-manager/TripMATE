package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.DailyPlanCntVO;
import com.tripmate.domain.DailyPlanDTO;
import com.tripmate.domain.DailyPlanVO;
import com.tripmate.domain.DeleteDailyPlanDTO;
import com.tripmate.domain.NotificationDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.DailyPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class DailyPlanApiServiceImpl implements DailyPlanApiService {

    @Override
    public boolean insertDailyPlan(DailyPlanDTO dailyPlanDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(DailyPlanService.class).insertDailyPlan(dailyPlanDTO);
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
    public List<String> deleteDailyPlan(DeleteDailyPlanDTO deleteDailyPlanDTO) throws Exception {
        Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(DailyPlanService.class).deleteDailyPlan(deleteDailyPlanDTO.getDailyPlanNo(), deleteDailyPlanDTO);
        List<String> result;

        ResponseWrapper<String> response = data.clone().execute().body();

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
    public List<DailyPlanCntVO> searchDailyPlanCntByDay(String planNo) throws Exception {
        Call<ResponseWrapper<DailyPlanCntVO>> data = RetrofitClient.getApiService(DailyPlanService.class).searchDailyPlanCntByDay(planNo);
        List<DailyPlanCntVO> result;

        ResponseWrapper<DailyPlanCntVO> response = data.clone().execute().body();

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
    public DailyPlanVO searchDailyPlanListByDay(String planNo, String memberNo, String dayGroup) throws Exception {
        Call<ResponseWrapper<DailyPlanVO>> data = RetrofitClient.getApiService(DailyPlanService.class).searchDailyPlanListByDay(planNo, memberNo, dayGroup);
        DailyPlanVO result;

        ResponseWrapper<DailyPlanVO> response = data.clone().execute().body();

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
    public boolean deleteDailyPlanNotification(String dailyPlanNo, String memberNo) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(DailyPlanService.class).deleteDailyPlanNotification(dailyPlanNo, memberNo);
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
    public boolean updateDailyPlanNotification(NotificationDTO notificationDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(DailyPlanService.class).updateDailyPlanNotification(notificationDTO.getDailyPlanNo(), notificationDTO);
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
}