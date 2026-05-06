package com.hospitalmanagement.controllers.authenticationcontroller;


import com.hospitalmanagement.dtos.userdtos.UserSignInRequestDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignInResponseDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignUpRequestDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignUpResponseDTO;
import com.hospitalmanagement.services.userservice.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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
        UserSignInResponseDTO response = userService.userSignIn(userSignInRequest);
        ResponseCookie cookie = ResponseCookie.from("token", response.getJwtToken())
                .httpOnly(true)      // JS cannot access (security)
                .secure(false)        // only HTTPS (set false for localhost)
                .path("/")
                .maxAge(60 * 5)     // 5 Min
                .sameSite("Strict")  // CSRF protection
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(response);

    }

    @PostMapping("/user/signup")
    public ResponseEntity<UserSignUpResponseDTO> userSignIn(@RequestBody @Valid UserSignUpRequestDTO userSignUpRequest){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.userSignUp(userSignUpRequest));


    }

    @PostMapping("/user/signout")
    public ResponseEntity<String> userSignOut(){
        ResponseCookie cookie = ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Logged out successfully");
    }

}
