package com.tripmate.service.apiservice;

import com.tripmate.domain.CheckListDTO;
import com.tripmate.domain.CheckListVO;
import com.tripmate.domain.DeleteCheckListDTO;

import java.util.List;

public interface CheckListApiService {
    boolean insertCheckList(CheckListDTO checkListDTO) throws Exception;
    List<CheckListVO> searchTogetherCheckList(String planNo) throws Exception;
    List<CheckListVO> searchMyCheckList(String planNo, String memberNo) throws Exception;
    boolean deleteCheckList(DeleteCheckListDTO deleteCheckListDTO) throws Exception;
}