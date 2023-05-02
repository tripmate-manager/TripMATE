package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.SearchPlanResultVO;
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
    public List<SearchPlanResultVO> searchPlanByKeyword(String memberNo, String keyword) throws Exception {
        Call<ResponseWrapper<SearchPlanResultVO>> data = RetrofitClient.getApiService(SearchPlanService.class).searchPlanByKeyword(memberNo, keyword);
        List<SearchPlanResultVO> result;

        ResponseWrapper<SearchPlanResultVO> response = data.clone().execute().body();

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
