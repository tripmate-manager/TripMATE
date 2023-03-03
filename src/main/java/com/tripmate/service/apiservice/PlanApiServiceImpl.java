package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.PlanDTO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
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
    public PlanVO getPlanInfo(String planNo) throws Exception {
        Call<ResponseWrapper<PlanVO>> data = RetrofitClient.getApiService(PlanService.class).getPlanInfo(planNo);
        PlanVO result;

        ResponseWrapper<PlanVO> response = data.clone().execute().body();

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
}
