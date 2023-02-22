package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.ChangePasswordDTO;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.MemberMailDTO;
import com.tripmate.domain.MypageDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.SignInDTO;
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
    public ResponseWrapper<Integer> signUp(MemberDTO memberDTO) {
        Call<ResponseWrapper<Integer>> data = RetrofitClient.getApiService(MemberService.class).signUp(memberDTO);
        ResponseWrapper<Integer> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<Boolean> isDuplicate(String type, String value) {
        Call<ResponseWrapper<Boolean>> data;
        ResponseWrapper<Boolean> response = null;

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

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<String> certificationMailConfirm(String memberId, String key, String mailTypeCode) {
        Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(MemberService.class).certificationMailConfirm(memberId, key, mailTypeCode);
        ResponseWrapper<String> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }

    @Override
    public ResponseWrapper<MemberDTO> signIn(SignInDTO signInDTO) {
        Call<ResponseWrapper<MemberDTO>> data = RetrofitClient.getApiService(MemberService.class).signIn(signInDTO);
        ResponseWrapper<MemberDTO> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<String> findId(String memberName, String email){
        Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(MemberService.class).findId(memberName, email);
        ResponseWrapper<String> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<Boolean> isSendMailSuccess(String type, MemberMailDTO memberMailDTO) {
        Call<ResponseWrapper<Boolean>> data;
        ResponseWrapper<Boolean> response = null;

        switch (type) {
            case ConstCode.EMAIL_TYPE_CODE_JOIN:
            case ConstCode.EMAIL_TYPE_CODE_EMAIL_CHANGE:
                data = RetrofitClient.getApiService(MemberService.class).sendCertificationMail(memberMailDTO);
                break;
            default:
                data = RetrofitClient.getApiService(MemberService.class).sendPasswordMail(memberMailDTO);
        }

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<Boolean> changePassword(ChangePasswordDTO changePasswordDTO) {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(MemberService.class).changePassword(changePasswordDTO);
        ResponseWrapper<Boolean> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<Boolean> withdraw(int memberNo) {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(MemberService.class).withdraw(memberNo);
        ResponseWrapper<Boolean> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }

    @Override
    public ResponseWrapper<MypageDTO> editMypageMemberInfo(int memberNo, MypageDTO mypageDTO) {
        Call<ResponseWrapper<MypageDTO>> data = RetrofitClient.getApiService(MemberService.class).updateMemberInfo(memberNo, mypageDTO);
        ResponseWrapper<MypageDTO> response = null;

        try {
            response = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }
}
