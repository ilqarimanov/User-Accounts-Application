package az.dev.useraccountsapplication.service.impl;

import az.dev.useraccountsapplication.dto.response.CommonResponse;
import az.dev.useraccountsapplication.dto.response.ErrorResponse;
import az.dev.useraccountsapplication.entity.AccountEntity;
import az.dev.useraccountsapplication.entity.UserEntity;
import az.dev.useraccountsapplication.repository.AccountRepository;
import az.dev.useraccountsapplication.repository.UserRepository;
import az.dev.useraccountsapplication.dto.request.AccountRequest;
import az.dev.useraccountsapplication.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommonResponse createAccount(AccountRequest accountRequest) throws RuntimeException {

        UserEntity user = userRepository.findUserEntityById(accountRequest.getUserId());
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserId(user);
        accountEntity.setAccountNumber(accountRequest.getAccountNumber());
        accountEntity.setBalance(accountRequest.getBalance());
        accountRepository.save(accountEntity);
        if(accountEntity == null){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Enter the information");
            errorResponse.setCode(150);
            return  new CommonResponse(errorResponse);
        }


        return new CommonResponse(ErrorResponse.getSuccessMessage());
    }

    @Override
    public CommonResponse accountList() {
        List<UserEntity> userEntities = userRepository.findAll();
        if (userEntities.size() == 0 || userEntities == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("User List is Empty");
            errorResponse.setCode(151);
            return new CommonResponse(errorResponse);
        }
        return new CommonResponse(ErrorResponse.getSuccessMessage(), userEntities);

    }


    @Override
    public List<AccountEntity> searchAccount(Long userId) {
        UserEntity user = userRepository.findUserEntityById(userId);
        return accountRepository.findAllByUserId(user);
    }
}


//            return accountRepository.findAccountEntitiesByUserId(accountList(userId));

//        List<AccountEntity> accountEntityList = accountRepository.findAccountEntitiesByUserId(userId);
//        if (accountEntityList.size() == 0 || accountEntityList == null) {
//            Status status = new Status();
//            status.setCode(123);
//            status.setMessage("Account is Empty");
//            return new CommonResponse<>(status);
//        }
//        return new CommonResponse<>(Status.getSuccessMessage(), accountEntityList);


//        List<AccountEntity> account = accountRepository.findAccountEntitiesByUserId(accountRequest.getUserId());
//        AccountEntity accountEntity = new AccountEntity();
//        accountEntity.setAccountNumber(accountRequest.getAccountNumber());
//        accountEntity.setBalance(accountRequest.getBalance());
//
//        return  new CommonResponse(Status.getSuccessMessage());
//        ------------------------------------------------------------------------------------------------

//        AccountEntity accountEntity = new AccountEntity();
//        accountEntity.setAccountNumber(accountEntity.getAccountNumber());
//        accountEntity.setBalance(accountEntity.getBalance());

//        ------------------------------------------------------------------------------------------------
//

//        return accountRepository.findById(userId)
//                .map(accountEntity -> new CommonResponse(Status.getSuccessMessage(), "ok"))
//                .orElseThrow(() -> new RuntimeException("Product with id " + userId + "was not founxd "));

//        ------------------------------------------------------------------------------------------------
//        accountEntity.setAccountNumber(accountEntity.getAccountNumber());
//        accountEntity.setBalance(accountEntity.getBalance());




