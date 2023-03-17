package com.tripmate.service.apiservice;

import com.tripmate.domain.PostDTO;
import com.tripmate.domain.PostVO;

import java.util.List;

public interface WishListApiService {
    String createPost(PostDTO postDTO) throws Exception;
    List<PostVO> searchWishList(String planNo) throws Exception;
}
