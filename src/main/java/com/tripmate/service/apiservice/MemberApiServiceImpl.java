package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.MemberMailDTO;
import com.tripmate.domain.MypageDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.SignInDTO;
import com.tripmate.domain.UpdatePasswordDTO;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.ConstCode;
import com.tripmate.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;

@Slf4j
@Service
public class MemberApiServiceImpl implements MemberApiService {

    @Override
    public int signUp(MemberDTO memberDTO) throws Exception {
        Call<ResponseWrapper<Integer>> data = RetrofitClient.getApiService(MemberService.class).signUp(memberDTO);
        int result;

        ResponseWrapper<Integer> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("api response data is empty");
        }
        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().size() != 1) {
                throw new IOException("response's data size is not 1");
            }
            if (response.getData().get(0) == 0) {
                throw new IOException("response's data is not valid");
            }

            log.debug("member no is {}", response.getData().get(0));
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new ApiCommonException(response.getCode(), response.getMessage());
        }

        return result;
    }

    @Override
    public Boolean isDuplicate(String type, String value) throws Exception {
        Call<ResponseWrapper<Boolean>> data;
        Boolean result;

        ResponseWrapper<Boolean> response;

        switch (type) {
            case ConstCode.DUPLICATION_CHECK_MEMBER_ID:
                data = RetrofitClient.getApiService(MemberService.class).isIdDuplicate(value);
                break;
            case ConstCode.DUPLICATION_CHECK_NICK_NAME:
                data = RetrofitClient.getApiService(MemberService.class).isNickNameDuplicate(value);
                break;
            default:
                data = RetrofitClient.getApiService(MemberService.class).isEmailDuplicate(value);
        }

        response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("api response data is empty");
        }

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
    public String certificationMailConfirm(String memberId, String key, String mailTypeCode) throws Exception {
        Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(MemberService.class).certificationMailConfirm(memberId, key, mailTypeCode);
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
            throw new IOException(response.getMessage());
        }

        return result;
    }

    @Override
    public MemberDTO signIn(SignInDTO signInDTO) throws Exception {
        Call<ResponseWrapper<MemberDTO>> data = RetrofitClient.getApiService(MemberService.class).signIn(signInDTO);
        MemberDTO result;

        ResponseWrapper<MemberDTO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("api response data is empty");
        } else {
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
        }

        return result;
    }

    @Override
    public String findId(String memberName, String email) throws Exception {
        Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(MemberService.class).findId(memberName, email);
        String result;

        ResponseWrapper<String> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

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
    public boolean isSendMailSuccess(String type, MemberMailDTO memberMailDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data;
        boolean result;

        switch (type) {
            case ConstCode.EMAIL_TYPE_CODE_JOIN:
            case ConstCode.EMAIL_TYPE_CODE_EMAIL_CHANGE:
                data = RetrofitClient.getApiService(MemberService.class).sendCertificationMail(memberMailDTO);
                break;
            default:
                data = RetrofitClient.getApiService(MemberService.class).sendPasswordMail(memberMailDTO);
        }

        ResponseWrapper<Boolean> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }
        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().size() != 1) {
                throw new IOException("response's data size is not 1");
            }
            if (response.getData().get(0) == null) {
                throw new IOException("response's data is Empty");
            }
            if (!response.getData().get(0)) {
                throw new IOException("failed to send email");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new ApiCommonException(response.getCode(), response.getMessage());
        }

        return result;
    }

    @Override
    public boolean updatePassword(UpdatePasswordDTO updatePasswordDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(MemberService.class).updatePassword(updatePasswordDTO);
        boolean result;

        ResponseWrapper<Boolean> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().size() != 1) {
                throw new IOException("response's data size is not 1");
            }
            if (response.getData().get(0) == null) {
                throw new IOException("response's data is Empty");
            }
            if (!response.getData().get(0)) {
                throw new IOException("failed to change password");
            }

            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new ApiCommonException(response.getCode(), response.getMessage());
        }

        return result;
    }

    @Override
    public boolean withdraw(int memberNo) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(MemberService.class).withdraw(memberNo);
        boolean result;

        ResponseWrapper<Boolean> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().size() != 1) {
                throw new IOException("response's data size is not 1");
            }
            if (response.getData().get(0) == null) {
                throw new IOException("response's data is Empty");
            }
            if (!response.getData().get(0)) {
                throw new IOException("failed to withdraw");
            }
            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
            throw new ApiCommonException(response.getCode(), response.getMessage());
        }

        return result;
    }

    @Override
    public MypageDTO editMypageMemberInfo(int memberNo, MypageDTO mypageDTO) throws Exception {
        Call<ResponseWrapper<MypageDTO>> data = RetrofitClient.getApiService(MemberService.class).updateMemberInfo(memberNo, mypageDTO);
        MypageDTO result;

        ResponseWrapper<MypageDTO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

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
}
