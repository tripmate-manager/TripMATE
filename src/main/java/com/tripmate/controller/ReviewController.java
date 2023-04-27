package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.common.exception.FileUploadException;
import com.tripmate.common.util.FileUploadUtil;
import com.tripmate.domain.DeleteReviewDTO;
import com.tripmate.domain.ReviewDTO;
import com.tripmate.domain.ReviewImageDTO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.Const;
import com.tripmate.entity.FileUploadEnum;
import com.tripmate.service.apiservice.ReviewApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/review", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewApiService reviewApiService;
    private final FileUploadUtil fileUploadUtil;

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
                               @RequestParam(value = "multipartFile", required = false) List<MultipartFile> multipartFileList) {
        ApiResult result;
        List<String> saveFiles = new ArrayList<>();

        try {
            List<ReviewImageDTO> reviewImageList = new ArrayList<>();

            for (MultipartFile file : multipartFileList) {
                if (!StringUtils.isEmpty(file.getOriginalFilename())) {
                    if (!fileUploadUtil.fileExtensionValidCheck(file)) {
                        throw new FileUploadException(FileUploadEnum.FILE_EXTENSION_EXCEPTION.getCode(), FileUploadEnum.FILE_EXTENSION_EXCEPTION.getMessage());
                    }

                    if (!file.isEmpty() && file.getOriginalFilename() != null) {
                        reviewImageList.add(fileUploadUtil.fileUpload(saveFiles, file));
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
        } catch (FileUploadException e) {
            fileUploadUtil.deleteFile(saveFiles);
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (ApiCommonException e) {
            fileUploadUtil.deleteFile(saveFiles);
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            fileUploadUtil.deleteFile(saveFiles);
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/reviewList")
    public String viewReviewList(Model model, @RequestParam(value = "dailyPlanNo") @NotBlank String dailyPlanNo,
                                 @RequestParam(value = "postTypeCode") @NotBlank String postTypeCode) {
        try {
            model.addAttribute("reviewList", reviewApiService.searchReviewList(dailyPlanNo));
            model.addAttribute("postTypeCode", postTypeCode);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return "reviews/reviewList";
    }

    @PostMapping("/deleteReview")
    public @ResponseBody String deleteReview(@Valid DeleteReviewDTO deleteReviewDTO) {
        ApiResult result;

        try {
            List<String> reviewImageNameList = reviewApiService.deleteReview(deleteReviewDTO);
            fileUploadUtil.deleteFile(reviewImageNameList);

            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeleteReviewSuccess", true);
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
            log.error(e.getMessage(), e);
        }

        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }
}
