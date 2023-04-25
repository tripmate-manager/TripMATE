package com.tripmate.controller;

import com.tripmate.common.exception.ApiCommonException;
import com.tripmate.domain.CheckListDTO;
import com.tripmate.domain.DeleteCheckListDTO;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.UpdateCheckYnDTO;
import com.tripmate.entity.ApiResult;
import com.tripmate.entity.ApiResultEnum;
import com.tripmate.entity.Const;
import com.tripmate.service.apiservice.CheckListApiService;
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
import javax.validation.constraints.NotBlank;

@Slf4j
@Controller
@RequestMapping(value = "/checkList", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class CheckListController {
    private final CheckListApiService checkListApiService;

    @PostMapping("/checkList")
    public @ResponseBody ModelAndView viewCheckList(HttpServletRequest request,
                                                    @RequestParam(value = "planNo") @NotBlank String planNo) {
        try {
            MemberDTO memberInfoSession = (MemberDTO) request.getSession().getAttribute(Const.MEMBER_INFO_SESSION);

            request.setAttribute("planNo", planNo);
            request.setAttribute("togetherCheckList", checkListApiService.searchTogetherCheckList(planNo));
            request.setAttribute("myCheckList", checkListApiService.searchMyCheckList(planNo, String.valueOf(memberInfoSession.getMemberNo())));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ModelAndView("checklist/checkList");
    }

    @PostMapping("/addCheckList")
    public @ResponseBody String addCheckList(@Valid CheckListDTO checkListDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isInsertCheckListSuccess", checkListApiService.insertCheckList(checkListDTO));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/deleteCheckList")
    public @ResponseBody String deleteCheckList(@Valid DeleteCheckListDTO deleteCheckListDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isDeleteCheckListSuccess", checkListApiService.deleteCheckList(deleteCheckListDTO));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }

    @PostMapping("/updateCheckYn")
    public @ResponseBody String updateCheckYn(@Valid UpdateCheckYnDTO updateCheckYnDTO) {
        ApiResult result;

        try {
            result = ApiResult.builder().code(ApiResultEnum.SUCCESS.getCode()).message(ApiResultEnum.SUCCESS.getMessage()).build();
            result.put("isUpdateCheckYnSuccess", checkListApiService.updateCheckYn(updateCheckYnDTO));
        } catch (ApiCommonException e) {
            result = ApiResult.builder().code(e.getResultCode()).message(e.getResultMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = ApiResult.builder().code(ApiResultEnum.UNKNOWN.getCode()).message(ApiResultEnum.UNKNOWN.getMessage()).build();
        }

        return result.toJson();
    }
}