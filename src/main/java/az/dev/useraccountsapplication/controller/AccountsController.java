package az.dev.useraccountsapplication.controller;

import az.dev.useraccountsapplication.dto.response.CommonResponse;
import az.dev.useraccountsapplication.dto.request.AccountRequest;
import az.dev.useraccountsapplication.service.AccountService;
import az.dev.useraccountsapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private final UserService userService;

    private final AccountService accountService;


    public AccountsController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;

    }


    @GetMapping("/accountlist")
    public CommonResponse accountList() {
        return accountService.accountList();
    }


    @PostMapping("/create-account")
    public CommonResponse createAccount(@RequestBody @Validated AccountRequest accountRequest) {
        return accountService.createAccount(accountRequest);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> searchAccount(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(accountService.searchAccount(userId));
    }
}
