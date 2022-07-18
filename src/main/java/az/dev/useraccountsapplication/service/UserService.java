package az.dev.useraccountsapplication.service;

import az.dev.useraccountsapplication.dto.request.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {
    ResponseEntity<?> signUp(UserRequest userRequest);






}
