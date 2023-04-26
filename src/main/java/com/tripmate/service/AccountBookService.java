package com.tripmate.service;

import com.tripmate.domain.AccountBookDTO;
import com.tripmate.domain.AccountBookVO;
import com.tripmate.domain.DeleteAccountBookDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.UpdateAccountBookDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountBookService {
    @GET("v1/accountbook/{planNo}/{dayGroup}")
    Call<ResponseWrapper<AccountBookVO>> searchAccountListByDay(@Path("planNo") String planNo, @Path("dayGroup") String dayGroup);
    @POST("v1/accountbook/{planNo}}/{dayGroup}")
    Call<ResponseWrapper<Boolean>> insertAccount(@Path("planNo") String planNo, @Path("dayGroup") String dayGroup, @Body AccountBookDTO accountBookDTO);
    @PUT("v1/accountbook/amount/{planNo}")
    Call<ResponseWrapper<Boolean>> updateAccountAmount(@Path("planNo") String planNo, @Body UpdateAccountBookDTO updateAccountBookDTO);
    @PUT("v1/accountbook/sort/{planNo}")
    Call<ResponseWrapper<Boolean>> updateAccountSortSequence(@Path("planNo") String planNo, @Body UpdateAccountBookDTO updateAccountBookDTO);
    @POST("v1/accountbook/delete-account")
    Call<ResponseWrapper<Boolean>> deleteAccount(@Body DeleteAccountBookDTO deleteAccountBookDTO);
}
