package com.hospitalmanagement.services.userservice;

import com.hospitalmanagement.dtos.userdtos.UserSignInRequestDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignInResponseDTO;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {
    @Nullable UserSignInResponseDTO userSignIn(@Valid UserSignInRequestDTO userSignInRequest);
    UserSignInResponseDTO handleOAuth2UserLogIn(OAuth2User user, String authorizedClientRegistrationId);
}
