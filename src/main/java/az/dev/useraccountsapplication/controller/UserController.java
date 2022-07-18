package az.dev.useraccountsapplication.controller;

import az.dev.useraccountsapplication.dto.request.UserRequest;
import az.dev.useraccountsapplication.dto.response.JwtResponse;
import az.dev.useraccountsapplication.enums.ErrorCodeEnum;
import az.dev.useraccountsapplication.exception.CustomNotFoundException;
import az.dev.useraccountsapplication.security.auth.JwtTokenUtil;
import az.dev.useraccountsapplication.service.UserService;
import az.dev.useraccountsapplication.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserServiceImpl userDetailsService;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody UserRequest request) throws Exception {
        try {
            authenticate(request.getUsername(), request.getPassword());
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(request.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            throw new CustomNotFoundException(ErrorCodeEnum.USER_NOT_FOUND);
        }
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserRequest user) {
        return ResponseEntity.ok(userDetailsService.signUp(user));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }





}


