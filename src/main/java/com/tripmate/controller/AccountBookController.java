package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.AccountBookDTO;
import com.tripmate.domain.AccountBookVO;
import com.tripmate.domain.DeleteAccountBookDTO;
import com.tripmate.domain.UpdateAccountBookDTO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.service.apiservice.AccountBookApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
@Controller
@RequestMapping(value = "/accountBook", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class AccountBookController {
    private final AccountBookApiService accountBookApiService;

    @PostMapping("/accountBook")
    public @ResponseBody ModelAndView viewAccountBook(HttpServletRequest request,
                                                    @RequestParam(value = "planNo") @NotBlank String planNo,
                                                    @RequestParam(value = "dayGroup", required = false) String dayGroup) {
        try {
            AccountBookVO accountList = accountBookApiService.searchAccountListByDay(planNo, StringUtils.isEmpty(dayGroup) ? "1" : dayGroup);

            request.setAttribute("planNo", planNo);
            request.setAttribute("accountList", accountList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ModelAndView("accountbook/accountBook");
    }

    @PostMapping("/addAccount")
    public @ResponseBody String addAccount(@Valid AccountBookDTO accountBookDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isInsertAccountSuccess", accountBookApiService.insertAccount(accountBookDTO));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/updateAmount")
    public @ResponseBody String updateAccountAmount(@Valid UpdateAccountBookDTO updateAccountBookDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isUpdateAmountSuccess", accountBookApiService.updateAccountAmount(updateAccountBookDTO));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/deleteAccount")
    public @ResponseBody String deleteAccount(@Valid DeleteAccountBookDTO deleteAccountBookDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeleteAccountSuccess", accountBookApiService.deleteAccount(deleteAccountBookDTO));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}