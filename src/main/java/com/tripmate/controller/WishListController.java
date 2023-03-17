package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.PostDTO;
import com.tripmate.domain.PostVO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.apiservice.WishListApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
