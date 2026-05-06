package com.hospitalmanagement.controllers.usercontroller;


import com.hospitalmanagement.dtos.userdtos.UserResponseDTO;
import com.hospitalmanagement.services.userservice.UserService;
import com.hospitalmanagement.services.userservice.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }


}
