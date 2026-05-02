package com.hospitalmanagement.controllers.authenticationcontroller;


import com.hospitalmanagement.dtos.userdtos.UserSignInRequestDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignInResponseDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignUpRequestDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignUpResponseDTO;
import com.hospitalmanagement.services.userservice.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final UserServiceImpl userService ;

    public AuthenticationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signin")
    public ResponseEntity<UserSignInResponseDTO> userSignIn(@RequestBody @Valid UserSignInRequestDTO userSignInRequest){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.userSignIn(userSignInRequest));

    }

    @PostMapping("/user/signup")
    public ResponseEntity<UserSignUpResponseDTO> userSignIn(@RequestBody @Valid UserSignUpRequestDTO userSignUpRequest){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.userSignUp(userSignUpRequest));

    }

}
