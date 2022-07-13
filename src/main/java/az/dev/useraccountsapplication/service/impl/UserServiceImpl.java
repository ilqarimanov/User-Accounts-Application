package az.dev.useraccountsapplication.service.impl;

import az.dev.useraccountsapplication.dto.response.CommonResponse;
import az.dev.useraccountsapplication.dto.response.ErrorResponse;
import az.dev.useraccountsapplication.entity.UserEntity;
import az.dev.useraccountsapplication.enums.ErrorCodeEnum;
import az.dev.useraccountsapplication.exception.CustomNotFoundException;
import az.dev.useraccountsapplication.repository.UserRepository;
import az.dev.useraccountsapplication.dto.request.UserRequest;
import az.dev.useraccountsapplication.service.UserService;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    private UserService userService;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public CommonResponse signUp(UserRequest userRequest) {


        UserEntity user = new UserEntity();

        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        user.setDob(userRequest.getDob());
        userRepository.save(user);

        return new CommonResponse(ErrorResponse.getSuccessMessage());
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return Optional.empty();
    }


    @Override
    public CommonResponse signIn(UserRequest userRequest) {
        UserEntity userEntity = userRepository.
                findUserEntitiesByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword())
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.USER_NOT_FOUND));


        return new CommonResponse(ErrorResponse.getSuccessMessage());


    }


}


