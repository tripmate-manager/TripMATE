package com.tripmate.service.apiservice;

import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.MemberMailDTO;
import com.tripmate.domain.MypageDTO;
import com.tripmate.domain.SignInDTO;
import com.tripmate.domain.UpdatePasswordDTO;

public interface MemberApiService {
    int signUp(MemberDTO memberDTO) throws Exception;
    Boolean isDuplicate(String type, String memberId) throws Exception;
    String certificationMailConfirm(String memberId, String key, String mailTypeCode) throws Exception;
    MemberDTO signIn(SignInDTO signInDTO) throws Exception;
    String findId(String memberName, String email) throws Exception;
    boolean isSendMailSuccess(String type, MemberMailDTO memberMailDTO) throws Exception;
    boolean updatePassword(UpdatePasswordDTO changePasswordDTO) throws Exception;
    boolean withdraw(int memberNo) throws Exception;
    MypageDTO editMypageMemberInfo(int memberNo, MypageDTO mypageDTO) throws Exception;
}