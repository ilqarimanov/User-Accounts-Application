package az.dev.useraccountsapplication.controller;

import az.dev.useraccountsapplication.dto.response.CommonResponse;
import az.dev.useraccountsapplication.dto.request.UserRequest;
import az.dev.useraccountsapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public CommonResponse signUp(@RequestBody @Validated UserRequest userRequest) {
        return userService.signUp(userRequest);

    }

    @PostMapping("/sign-in")
    public CommonResponse signIn(@RequestBody @Validated UserRequest userRequest) {
        return userService.signIn(userRequest);
    }

}


