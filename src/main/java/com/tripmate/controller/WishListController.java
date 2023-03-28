package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.CommentDTO;
import com.tripmate.domain.CommentVO;
import com.tripmate.domain.DeleteCommentDTO;
import com.tripmate.domain.PostDTO;
import com.tripmate.domain.PostVO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.apiservice.WishListApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/wishlist", produces = "application/json; charset=utf8")
public class WishListController {
    private final WishListApiService wishListApiService;

    @Autowired
    public WishListController(WishListApiService wishListApiService) {
        this.wishListApiService = wishListApiService;
    }

    @PostMapping("/wishlist")
    public ModelAndView wishList(HttpServletRequest request, @RequestParam(value = "planNo") String planNo) {
        try {
            List<PostVO> wishList = wishListApiService.searchWishList(planNo);

            request.setAttribute("planNo", planNo);
            request.setAttribute("wishList", wishList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("wishlist/wishList");
    }

    @PostMapping("/createPost")
    public ModelAndView viewCreatePost(HttpServletRequest request, @RequestParam(value = "planNo") String planNo) {
        try {
            request.setAttribute("planNo", planNo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("wishlist/createPost");
    }

    @PostMapping("/createPost/callApi")
    public @ResponseBody String createPost(@Valid PostDTO postDTO) {
        ApiResult result;

        try {
            String createPostNo = wishListApiService.createPost(postDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("createPostNo", createPostNo);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/postMain")
    public ModelAndView viewPostMain(HttpServletRequest request, @RequestParam(value = "postNo") String postNo) {
        try {
            PostVO postInfo = wishListApiService.getPostInfo(postNo);
            List<CommentVO> commentList = wishListApiService.searchCommentList(postNo);

            request.setAttribute("postInfo", postInfo);
            request.setAttribute("commentList", commentList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("wishlist/postMain");
    }

    @PostMapping("/createComment")
    public @ResponseBody String createComment(@Valid CommentDTO commentDTO) {
        ApiResult result;

        try {
            String createCommentNo = wishListApiService.createComment(commentDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("createCommentNo", createCommentNo);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/deleteComment")
    public @ResponseBody String deleteComment(@Valid DeleteCommentDTO deleteCommentDTO) {
        ApiResult result;

        try {
            boolean isDeleteCommentSuccess = wishListApiService.deleteComment(deleteCommentDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeleteCommentSuccess", isDeleteCommentSuccess);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/editPost")
    public ModelAndView viewEditPost(HttpServletRequest request, @RequestParam(value = "postNo") String postNo) {
        try {
            PostVO postInfo = wishListApiService.getPostInfo(postNo);

            request.setAttribute("postInfo", postInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("wishlist/editPost");
    }

    @PostMapping("/editPost/callApi")
    public @ResponseBody String editPost(HttpServletRequest request, @Valid PostDTO postDTO) {
        ApiResult result;

        try {
            boolean isUpdatePostSuccess = wishListApiService.updatePost(postDTO);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isUpdatePostSuccess", isUpdatePostSuccess);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/deletePost")
    public @ResponseBody String deletePost(@RequestParam(value = "postNo") String postNo) {
        ApiResult result;

        try {
            boolean isDeletePostSuccess = wishListApiService.deletePost(postNo);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeletePostSuccess", isDeletePostSuccess);
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}
