package com.hospitalmanagement.services.userservice;

import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import com.hospitalmanagement.dtos.userdtos.*;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface UserService {
    UserSignInResponseDTO userSignIn(@Valid UserSignInRequestDTO userSignInRequest);
    UserSignInResponseDTO handleOAuth2UserLogIn(OAuth2User user, String authorizedClientRegistrationId);
    UserSignUpResponseDTO userSignUp(@Valid UserSignUpRequestDTO userSignUpRequest);
    List<UserResponseDTO> getAllRegisteredUser();
    AssignRoleResponseDTO assignRole(AssignRoleRequestDTO assignRoleRequestDTO);
    UserResponseDTO getRegisteredUser(String userId);
    Boolean deleteUserById(String userId);
    String toggleUserStatus(String userId);
    GetPatientResponseDTO onBoardPatientByUserId(String userId);
}
