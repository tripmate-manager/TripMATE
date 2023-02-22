package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.CreatePlanDTO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanVO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;

@Slf4j
@Service
public class PlanApiServiceImpl implements PlanApiService {
    @Override
    public ResponseWrapper<PlanAttributeVO> selectPlanAttributeList(String attributeTypeCode) {
        Call<ResponseWrapper<PlanAttributeVO>> data = RetrofitClient.getApiService(PlanService.class).selectPlanAttributeList(attributeTypeCode);
        ResponseWrapper<PlanAttributeVO> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<PlanAddressVO> selectAddressList() {
        Call<ResponseWrapper<PlanAddressVO>> data = RetrofitClient.getApiService(PlanService.class).selectAddressList();
        ResponseWrapper<PlanAddressVO> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<PlanAddressVO> selectAddressList(String sidoName) {
        Call<ResponseWrapper<PlanAddressVO>> data = RetrofitClient.getApiService(PlanService.class).selectAddressList(sidoName);
        ResponseWrapper<PlanAddressVO> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<Boolean> createPlan(CreatePlanDTO createPlanDTO) {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(PlanService.class).createPlan(createPlanDTO);
        ResponseWrapper<Boolean> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<PlanVO> searchMemberPlanList(String memberNo) {
        Call<ResponseWrapper<PlanVO>> data = RetrofitClient.getApiService(PlanService.class).searchMemberPlanList(memberNo);
        ResponseWrapper<PlanVO> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }
}
