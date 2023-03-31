package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.DailyPlanDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.DailyPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;

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
}