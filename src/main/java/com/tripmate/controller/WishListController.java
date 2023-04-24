package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.CommentDTO;
import com.tripmate.domain.DeleteCommentDTO;
import com.tripmate.domain.PlanVO;
import com.tripmate.domain.PostDTO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.apiservice.PlanApiService;
import com.tripmate.service.apiservice.WishListApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/wishlist", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class WishListController {
    private final WishListApiService wishListApiService;
    private final PlanApiService planApiService;

    @PostMapping("/wishlist")
    public @ResponseBody ModelAndView wishList(HttpServletRequest request, @RequestParam(value = "planNo") String planNo) {
        try {
            request.setAttribute("planNo", planNo);
            PlanVO planVO = planApiService.getPlanInfo(planNo);

            request.setAttribute("tripStartDate", planVO.getTripStartDate());
            request.setAttribute("tripTerm", String.valueOf(planVO.getTripTerm()));
            request.setAttribute("wishList", wishListApiService.searchWishList(planNo));
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
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("createPostNo", wishListApiService.createPost(postDTO));
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
            request.setAttribute("postInfo", wishListApiService.getPostInfo(postNo));
            request.setAttribute("commentList", wishListApiService.searchCommentList(postNo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("wishlist/postMain");
    }

    @PostMapping("/createComment")
    public @ResponseBody String createComment(@Valid CommentDTO commentDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("createCommentNo", wishListApiService.createComment(commentDTO));
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
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeleteCommentSuccess", wishListApiService.deleteComment(deleteCommentDTO));
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
            request.setAttribute("postInfo", wishListApiService.getPostInfo(postNo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("wishlist/editPost");
    }

    @PostMapping("/editPost/callApi")
    public @ResponseBody String editPost(HttpServletRequest request, @Valid PostDTO postDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isUpdatePostSuccess ", wishListApiService.updatePost(postDTO));
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
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeletePostSuccess", wishListApiService.deletePost(postNo));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}
