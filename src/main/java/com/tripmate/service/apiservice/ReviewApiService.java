package com.tripmate.service.apiservice;

import com.tripmate.domain.DeleteReviewDTO;
import com.tripmate.domain.ReviewDTO;
import com.tripmate.domain.ReviewVO;

import java.util.List;

public interface ReviewApiService {
    String insertReview(ReviewDTO reviewDTO) throws Exception;
    List<ReviewVO> searchReviewList(String dailyPlanNo) throws Exception;
    List<String> deleteReview(DeleteReviewDTO deleteReviewDTO) throws Exception;
}
