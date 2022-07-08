package az.dev.useraccountsapplication.service.impl;

import az.dev.useraccountsapplication.dto.response.CommonResponse;
import az.dev.useraccountsapplication.dto.response.ErrorResponse;
import az.dev.useraccountsapplication.entity.UserEntity;
import az.dev.useraccountsapplication.repository.UserRepository;
import az.dev.useraccountsapplication.dto.request.UserRequest;
import az.dev.useraccountsapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Map<String, String> users = new HashMap<>();


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
        UserEntity userEntity = userRepository.findUserEntitiesByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword());
        if (userEntity == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(150);
            errorResponse.setMessage("User Not Found");
            return new CommonResponse(errorResponse);
        }
        return new CommonResponse(ErrorResponse.getSuccessMessage());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (users.containsKey(username)) {
            return new User(username, users.get(username), new ArrayList<>());
        }
        throw new UsernameNotFoundException(username);
    }

    @PostConstruct
    public void init() {
        users.put("admin", passwordEncoder.encode("12345"));
    }


}


