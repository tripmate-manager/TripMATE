package com.tripmate.service.apiservice;

import com.tripmate.domain.AccountBookDTO;
import com.tripmate.domain.AccountBookVO;
import com.tripmate.domain.DeleteAccountBookDTO;
import com.tripmate.domain.UpdateAccountBookDTO;

public interface AccountBookApiService {
    AccountBookVO searchAccountListByDay(String planNo, String dayGroup) throws Exception;
    boolean insertAccount(AccountBookDTO accountBookDTO) throws Exception;
    boolean updateAccountAmount(UpdateAccountBookDTO updateAccountBookDTO) throws Exception;
    boolean updateAccountSortSequence(UpdateAccountBookDTO updateAccountBookDTO) throws Exception;
    boolean deleteAccount(DeleteAccountBookDTO deleteAccountBookDTO) throws Exception;
}
