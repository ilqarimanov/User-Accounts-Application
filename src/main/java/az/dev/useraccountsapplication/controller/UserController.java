package az.dev.useraccountsapplication.controller;

import az.dev.useraccountsapplication.auth.TokenManager;
import az.dev.useraccountsapplication.dto.response.CommonResponse;
import az.dev.useraccountsapplication.dto.request.UserRequest;
import az.dev.useraccountsapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public CommonResponse signUp(@RequestBody @Validated UserRequest userRequest) {

        return userService.signUp(userRequest);

    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Validated UserRequest userRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getUsername(),userRequest.getPassword()));
            return ResponseEntity.ok(tokenManager.generateToken(userRequest.getUsername()));
        } catch (Exception e) {
            throw e;
        }


    }

}

//        return userService.signIn(userRequest);
