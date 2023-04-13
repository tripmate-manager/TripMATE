package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.DeleteReviewDTO;
import com.tripmate.domain.ReviewDTO;
import com.tripmate.domain.ReviewImageDTO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.Const;
import com.tripmate.service.apiservice.ReviewApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping(value = "/review", produces = "application/json; charset=utf8")
public class ReviewController {
    private final ReviewApiService reviewApiService;

    @Autowired
    public ReviewController(ReviewApiService reviewApiService) {
        this.reviewApiService = reviewApiService;
    }

    @PostMapping("/createReview")
    public String viewCreateReview(Model model, @RequestParam(value = "dailyPlanNo") @NotBlank String dailyPlanNo, @RequestParam(value = "postTypeCode") @NotBlank String postTypeCode) {
        try {
            model.addAttribute("dailyPlanNo", dailyPlanNo);
            model.addAttribute("postTypeCode", postTypeCode);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "reviews/createReview";
    }

    @PostMapping("/createReview/callApi")
    public @ResponseBody String createReview(@Valid ReviewDTO reviewDTO,
                               @RequestParam(value = "multipartFile", required = false) List<MultipartFile> multipartFile) {
        ApiResult result;
        List<ReviewImageDTO> reviewImageList = new ArrayList<>();

        try {
            for (MultipartFile file : multipartFile) {
                if (!file.isEmpty()) {
                    System.out.println(file.getOriginalFilename() + " / " + file.getName());
                    if (file.getOriginalFilename() != null) {

                        String originalName = file.getOriginalFilename();
                        String saveFileName = UUID.randomUUID() + "_" + originalName.substring(originalName.lastIndexOf("\\") + 1);

                        reviewImageList.add(ReviewImageDTO.builder()
                                .reviewNo(reviewDTO.getReviewNo())
                                .reviewImageName(saveFileName)
                                .reviewImagePath(File.separator + saveFileName)
                                .reviewImageVolume(String.valueOf(file.getSize()))
                                .build());

                        file.transferTo(new File(Const.FILE_DIR_PATH, saveFileName));
                    }
                }
            }

            ReviewDTO reviewRequestDTO = ReviewDTO.builder()
                    .memberNo(reviewDTO.getMemberNo())
                    .dailyPlanNo(reviewDTO.getDailyPlanNo())
                    .postTypeCode(reviewDTO.getPostTypeCode())
                    .scoreLocation(reviewDTO.getScoreLocation())
                    .scoreAmount(reviewDTO.getScoreAmount())
                    .scoreAll(reviewDTO.getScoreAll())
                    .scoreFacility(reviewDTO.getScoreFacility())
                    .scoreSanitary(reviewDTO.getScoreSanitary())
                    .scoreService(reviewDTO.getScoreService())
                    .scoreFood(reviewDTO.getScoreFood())
                    .reviewContents(reviewDTO.getReviewContents())
                    .reviewAverageScore(reviewDTO.getReviewAverageScore())
                    .reviewImageList(reviewImageList)
                    .build();

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("createReviewNo", reviewApiService.insertReview(reviewRequestDTO));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        //todo: 페이지 이동 수정
        return result.toJson();
    }

    @PostMapping("/reviewList")
    public String viewReviewList(Model model, @RequestParam(value = "dailyPlanNo") @NotBlank String dailyPlanNo) {
        try {
            model.addAttribute("reviewList", reviewApiService.searchReviewList(dailyPlanNo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return "reviews/reviewList";
    }

    @PostMapping("/deleteReview")
    public @ResponseBody
    String deleteReview(@Valid DeleteReviewDTO deleteReviewDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeleteReviewSuccess", reviewApiService.deleteReview(deleteReviewDTO));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }


    @GetMapping("/display")
    public ResponseEntity<Resource> display(@RequestParam(value = "filename") String filename) {
        Resource resource = new FileSystemResource(Const.FILE_DIR_PATH + filename);
        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders header = new HttpHeaders();
        Path filePath;

        try {
            filePath = Paths.get(Const.FILE_DIR_PATH + "/" + filename);
            header.add("Content-type", Files.probeContentType(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }
}
