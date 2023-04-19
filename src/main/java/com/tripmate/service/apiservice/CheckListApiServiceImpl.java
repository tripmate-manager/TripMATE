package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.CheckListDTO;
import com.tripmate.domain.CheckListVO;
import com.tripmate.domain.DeleteCheckListDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.UpdateCheckYnDTO;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.CheckListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class CheckListApiServiceImpl implements CheckListApiService {
    @Override
    public boolean insertCheckList(CheckListDTO checkListDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(CheckListService.class).insertCheckList(checkListDTO.getPlanNo(), checkListDTO);
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
    public List<CheckListVO> searchTogetherCheckList(String planNo) throws Exception {
        Call<ResponseWrapper<CheckListVO>> data = RetrofitClient.getApiService(CheckListService.class).searchTogetherCheckList(planNo);
        List<CheckListVO> result;

        ResponseWrapper<CheckListVO> response = data.clone().execute().body();

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
    public List<CheckListVO> searchMyCheckList(String planNo, String memberNo) throws Exception {
        Call<ResponseWrapper<CheckListVO>> data = RetrofitClient.getApiService(CheckListService.class).searchMyCheckList(planNo, memberNo);
        List<CheckListVO> result;

        ResponseWrapper<CheckListVO> response = data.clone().execute().body();

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
    public boolean deleteCheckList(DeleteCheckListDTO deleteCheckListDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(CheckListService.class).deleteCheckList(deleteCheckListDTO);
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
    public boolean updateCheckYn(UpdateCheckYnDTO updateCheckYnDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(CheckListService.class).updateCheckYn(updateCheckYnDTO.getMaterialNo(), updateCheckYnDTO);
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