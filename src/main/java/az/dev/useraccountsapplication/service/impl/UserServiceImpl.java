package az.dev.useraccountsapplication.service.impl;

import az.dev.useraccountsapplication.dto.response.ErrorResponse;
import az.dev.useraccountsapplication.entity.UserEntity;
import az.dev.useraccountsapplication.repository.UserRepository;
import az.dev.useraccountsapplication.dto.request.UserRequest;
import az.dev.useraccountsapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public ResponseEntity<?> signUp(UserRequest userRequest) {

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


        return ResponseEntity.ok(ErrorResponse.getSuccessMessage());


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


