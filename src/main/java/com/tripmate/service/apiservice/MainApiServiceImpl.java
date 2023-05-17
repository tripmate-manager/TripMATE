package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.PlanBasicInfoVO;
import com.tripmate.domain.PopularPlanVO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class MainApiServiceImpl implements MainApiService {
    @Override
    public List<PopularPlanVO> searchPopularPlanList() throws Exception {
        Call<ResponseWrapper<PopularPlanVO>> data = RetrofitClient.getApiService(MainService.class).searchPopularPlanList();
        List<PopularPlanVO> result;

        ResponseWrapper<PopularPlanVO> response = data.clone().execute().body();

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
    public List<PopularPlanVO> searchPopularPlanList(String memberNo) throws Exception {
        Call<ResponseWrapper<PopularPlanVO>> data = RetrofitClient.getApiService(MainService.class).searchPopularPlanList(memberNo);
        List<PopularPlanVO> result;

        ResponseWrapper<PopularPlanVO> response = data.clone().execute().body();

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
    public List<PlanBasicInfoVO> searchUserRecommendationPlanList(String memberNo) throws Exception {
        Call<ResponseWrapper<PlanBasicInfoVO>> data = RetrofitClient.getApiService(MainService.class).searchUserRecommendationPlanList(memberNo);
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

    @Override
    public List<PlanBasicInfoVO> searchRecommendationPlanList() throws Exception {
        Call<ResponseWrapper<PlanBasicInfoVO>> data = RetrofitClient.getApiService(MainService.class).searchRecommendationPlanList();
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
