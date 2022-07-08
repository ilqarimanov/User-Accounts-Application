package az.dev.useraccountsapplication.service;

import az.dev.useraccountsapplication.dto.response.CommonResponse;
//import az.dev.useraccountsapplication.dto.response.UserResponse;
import az.dev.useraccountsapplication.dto.request.UserRequest;
import az.dev.useraccountsapplication.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    CommonResponse signUp(UserRequest userRequest);

    Optional<UserEntity> findByUsername(String username);


    CommonResponse signIn(UserRequest userRequest);



}
