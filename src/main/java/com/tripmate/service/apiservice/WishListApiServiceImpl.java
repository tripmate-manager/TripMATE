package com.tripmate.service.apiservice;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.CommentDTO;
import com.tripmate.domain.CommentVO;
import com.tripmate.domain.DeleteCommentDTO;
import com.tripmate.domain.PostDTO;
import com.tripmate.domain.PostVO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.WishListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class WishListApiServiceImpl implements WishListApiService {
    @Override
    public String createPost(PostDTO postDTO) throws Exception {
        Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(WishListService.class).createPost(postDTO);
        String result;

        ResponseWrapper<String> response = data.clone().execute().body();

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
    public List<PostVO> searchWishList(String planNo) throws Exception {
        Call<ResponseWrapper<PostVO>> data = RetrofitClient.getApiService(WishListService.class).searchWishList(planNo);
        List<PostVO> result;

        ResponseWrapper<PostVO> response = data.clone().execute().body();

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
    public PostVO getPostInfo(String postNo) throws Exception {
        Call<ResponseWrapper<PostVO>> data = RetrofitClient.getApiService(WishListService.class).getPostInfo(postNo);
        PostVO result;

        ResponseWrapper<PostVO> response = data.clone().execute().body();

        if (response == null) {
            throw new IOException("response is Empty");
        }

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData() == null || response.getData().get(0) == null) {
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
    public String createComment(CommentDTO commentDTO) throws Exception {
        Call<ResponseWrapper<String>> data = RetrofitClient.getApiService(WishListService.class).createComment(commentDTO);
        String result;

        ResponseWrapper<String> response = data.clone().execute().body();

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
    public List<CommentVO> searchCommentList(String postNo) throws Exception {
        Call<ResponseWrapper<CommentVO>> data = RetrofitClient.getApiService(WishListService.class).searchCommentList(postNo);
        List<CommentVO> result;

        ResponseWrapper<CommentVO> response = data.clone().execute().body();

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
    public boolean deleteComment(DeleteCommentDTO deleteCommentDTO) throws Exception {
        Call<ResponseWrapper<Boolean>> data = RetrofitClient.getApiService(WishListService.class).deleteComment(deleteCommentDTO.getCommentNo(), deleteCommentDTO);
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
