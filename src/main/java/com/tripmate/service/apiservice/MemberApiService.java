package com.tripmate.service.apiservice;

import com.tripmate.domain.ChangePasswordDTO;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.MemberMailDTO;
import com.tripmate.domain.MypageDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.SignInDTO;

public interface MemberApiService {
    ResponseWrapper<Integer> signUp(MemberDTO memberDTO);
    ResponseWrapper<Boolean> isDuplicate(String type, String memberId);
    ResponseWrapper<String> certificationMailConfirm(String memberId, String key, String mailTypeCode);
    ResponseWrapper<MemberDTO> signIn(SignInDTO signInDTO);
    ResponseWrapper<String> findId(String memberName, String email);
    ResponseWrapper<Boolean> isSendMailSuccess(String type, MemberMailDTO memberMailDTO);
    ResponseWrapper<Boolean> changePassword(ChangePasswordDTO changePasswordDTO);
    ResponseWrapper<Boolean> withdraw(int memberNo);
    ResponseWrapper<MypageDTO> editMypageMemberInfo(int memberNo, MypageDTO mypageDTO);
}