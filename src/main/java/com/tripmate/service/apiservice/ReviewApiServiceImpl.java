package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.DeleteReviewDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.ReviewDTO;
import com.tripmate.domain.ReviewVO;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ReviewApiServiceImpl implements ReviewApiService {

    @Override
    public String insertReview(ReviewDTO reviewDTO) throws Exception {
        Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(ReviewService.class).insertReview(reviewDTO.getDailyPlanNo(), reviewDTO);
        String result;

        ResponseWrapper<String> response = data.clone().execute().body();

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
    public List<ReviewVO> searchReviewList(String dailyPlanNo) throws Exception {
        Call<ResponseWrapper<ReviewVO>> data = RetrofitClient.getApiService(ReviewService.class).searchReviewList(dailyPlanNo);
        List<ReviewVO> result;

        ResponseWrapper<ReviewVO> response = data.clone().execute().body();

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
    public Boolean deleteReview(DeleteReviewDTO deleteReviewDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(ReviewService.class).deleteReview(deleteReviewDTO);
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
            throw new ApiCommonException(response.getCode(), response.getMessage());
        }

        return result;
    }
}
