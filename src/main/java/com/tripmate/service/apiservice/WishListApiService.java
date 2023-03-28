package com.tripmate.service.apiservice;

import com.tripmate.domain.CommentDTO;
import com.tripmate.domain.CommentVO;
import com.tripmate.domain.DeleteCommentDTO;
import com.tripmate.domain.PostDTO;
import com.tripmate.domain.PostVO;

import java.util.List;

public interface WishListApiService {
    String createPost(PostDTO postDTO) throws Exception;
    List<PostVO> searchWishList(String planNo) throws Exception;
    PostVO getPostInfo(String postNo) throws Exception;
    String createComment(CommentDTO commentDTO) throws Exception;
    List<CommentVO> searchCommentList(String postNo) throws Exception;
    boolean deleteComment(DeleteCommentDTO deleteCommentDTO) throws Exception;
}
