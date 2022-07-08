package az.dev.useraccountsapplication.service;

import az.dev.useraccountsapplication.dto.response.CommonResponse;
import az.dev.useraccountsapplication.entity.AccountEntity;
import az.dev.useraccountsapplication.dto.request.AccountRequest;

import java.util.List;

public interface AccountService {


    CommonResponse createAccount(AccountRequest accountRequest);

    CommonResponse accountList();

    List<AccountEntity>searchAccount(Long userId);

}
