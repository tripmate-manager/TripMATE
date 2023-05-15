package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.PlanBasicInfoVO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.SearchAttributeDTO;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.SearchPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class SearchPlanApiServiceImpl implements SearchPlanApiService {

    @Override
    public List<PlanBasicInfoVO> searchPlanByKeyword(String memberNo, String keyword) throws Exception {
        Call<ResponseWrapper<PlanBasicInfoVO>> data = RetrofitClient.getApiService(SearchPlanService.class).searchPlanByKeyword(memberNo, keyword);
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
    public List<PlanBasicInfoVO> searchPlanByAttribute(SearchAttributeDTO searchAttributeDTO) throws Exception {
        Call<ResponseWrapper<PlanBasicInfoVO>> data = RetrofitClient.getApiService(SearchPlanService.class).searchPlanByAttribute(searchAttributeDTO);
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
    public List<String> searchPopularSearchKeyword() throws Exception {
        Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(SearchPlanService.class).searchPopularSearchKeyword();
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
    public List<String> searchPopularHashtag() throws Exception {
        Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(SearchPlanService.class).searchPopularHashtag();
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
}
