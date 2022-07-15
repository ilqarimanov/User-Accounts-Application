package az.dev.useraccountsapplication.service.impl;

import az.dev.useraccountsapplication.dto.response.CommonResponse;
import az.dev.useraccountsapplication.dto.response.ErrorResponse;
import az.dev.useraccountsapplication.entity.UserEntity;
import az.dev.useraccountsapplication.enums.ErrorCodeEnum;
import az.dev.useraccountsapplication.exception.CustomNotFoundException;
import az.dev.useraccountsapplication.repository.UserRepository;
import az.dev.useraccountsapplication.dto.request.UserRequest;
import az.dev.useraccountsapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    private UserService userService;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public CommonResponse signUp(UserRequest userRequest) {

        UserEntity newUser = new UserEntity();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(bcryptEncoder.encode(userRequest.getPassword()));
        newUser.setName(userRequest.getName());
        newUser.setSurname(userRequest.getSurname());
        newUser.setEmail(userRequest.getEmail());
        newUser.setAddress(userRequest.getAddress());
        newUser.setPhone(userRequest.getPhone());
        newUser.setDob(userRequest.getDob());
        userRepository.save(newUser);

        return new CommonResponse(ErrorResponse.getSuccessMessage());



//        UserEntity user = new UserEntity();
//
//        user.setUsername(userRequest.getUsername());
//        user.setPassword(userRequest.getPassword());
//        user.setName(userRequest.getName());
//        user.setSurname(userRequest.getSurname());
//        user.setEmail(userRequest.getEmail());
//        user.setAddress(userRequest.getAddress());
//        user.setPhone(userRequest.getPhone());
//        user.setDob(userRequest.getDob());
//        userRepository.save(user);
//
//        return new CommonResponse(ErrorResponse.getSuccessMessage());
    }




    @Override
    public CommonResponse signIn(UserRequest userRequest) {
        UserEntity userEntity = userRepository.
                findUserEntitiesByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword())
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.USER_NOT_FOUND));


        return new CommonResponse(ErrorResponse.getSuccessMessage());


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}


