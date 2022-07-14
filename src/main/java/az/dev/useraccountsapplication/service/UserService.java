package az.dev.useraccountsapplication.service;

import az.dev.useraccountsapplication.dto.response.CommonResponse;
import az.dev.useraccountsapplication.dto.request.UserRequest;
import az.dev.useraccountsapplication.entity.UserEntity;

import java.util.Optional;


public interface UserService {
    CommonResponse signUp(UserRequest userRequest);

    CommonResponse signIn(UserRequest userRequest);



}
