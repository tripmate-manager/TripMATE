package com.tripmate.service;

import com.tripmate.domain.ExitPlanDTO;
import com.tripmate.domain.InviteCodeVO;
import com.tripmate.domain.NotificationDTO;
import com.tripmate.domain.NotificationVO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanBasicInfoVO;
import com.tripmate.domain.PlanDTO;
import com.tripmate.domain.PlanMateDTO;
import com.tripmate.domain.PlanMateVO;
import com.tripmate.domain.PlanVO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlanService {
    @GET("v1/plans/plan-attributes")
    Call<ResponseWrapper<PlanAttributeVO>> selectPlanAttributeList(@Query("attributeTypeCode") String attributeTypeCode);
    @GET("v1/plans/trip-address")
    Call<ResponseWrapper<PlanAddressVO>> selectAddressList();
    @GET("v1/plans/trip-address/{sidoName}")
    Call<ResponseWrapper<PlanAddressVO>> selectAddressList(@Path("sidoName") String sidoName);
    @POST("v1/plans/create-plan")
    Call<ResponseWrapper<Integer>> createPlan(@Body PlanDTO planDTO);
    @GET("v1/plans/{memberNo}")
    Call<ResponseWrapper<PlanVO>> searchMemberPlanList(@Path("memberNo") String memberNo);
    @GET("v1/plans/plan-detail/{planNo}")
    Call<ResponseWrapper<PlanVO>> getPlanInfo(@Path("planNo") String planNo, @Query("memberNo") String memberNo);
    @GET("v1/plans/plan-mate/{planNo}")
    Call <ResponseWrapper<PlanMateVO>> searchPlanMateList(@Path("planNo") String planNo);
    @PUT("v1/plans/{planNo}")
    Call<ResponseWrapper<Boolean>> updatePlan(@Path("planNo") String planNo, @Body PlanDTO planDTO);
    @GET("v1/plans/search-member")
    Call<ResponseWrapper<PlanMateVO>> searchMemberList(@Query("searchDiviCode") String searchDiviCode, @Query("searchKeyword") String searchKeyword);
    @POST("v1/plans/invite-code")
    Call<ResponseWrapper<InviteCodeVO>> createInviteAuthCode(@Query("planNo") String planNo, @Query("memberNo") String memberNo, @Query("inviteTypeCode") String inviteTypeCode);
    @POST("v1/plans/notification")
    Call <ResponseWrapper<Boolean>> createNotification(@Body NotificationDTO notificationDTO);
    @GET("v1/plans/notification")
    Call <ResponseWrapper<NotificationVO>> searchNotificationList(@Query("memberNo") String memberNo);
    @GET("v1/plans/notification/unread")
    Call <ResponseWrapper<Integer>> getUnreadNotificationCnt(@Query("memberNo") String memberNo);
    @PUT("v1/plans/notification")
    Call <ResponseWrapper<Boolean>> updateNotificationReadDateTime(@Query("memberNo") String memberNo, @Query("notificationNo") String notificationNo);
    @POST("v1/plans/exit-plan")
    Call <ResponseWrapper<Boolean>> exitPlan(@Body ExitPlanDTO exitPlanDTO);
    @GET("v1/plans/invite-code")
    Call<ResponseWrapper<InviteCodeVO>> getInviteCodeInfo(@Query("inviteCodeNo") String inviteCodeNo);
    @POST("v1/plans/plan-mate")
    Call <ResponseWrapper<Boolean>> insertPlanMate(@Body PlanMateDTO planMateDTO);
    @POST("v1/plans/plan-like")
    Call <ResponseWrapper<Boolean>> insertPlanLike(@Query("planNo") String planNo, @Query("memberNo") String memberNo);
    @DELETE("v1/plans/plan-like")
    Call <ResponseWrapper<Boolean>> deletePlanLike(@Query("planNo") String planNo, @Query("memberNo") String memberNo);
    @GET("v1/plans/plan-like/{memberNo}")
    Call <ResponseWrapper<PlanBasicInfoVO>> searchMyPlanLikeList(@Path("memberNo") String memberNo);
}
