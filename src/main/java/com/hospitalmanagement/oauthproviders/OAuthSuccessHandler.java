package com.hospitalmanagement.oauthproviders;

import com.hospitalmanagement.dtos.userdtos.UserSignInRequestDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignInResponseDTO;
import com.hospitalmanagement.services.userservice.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService ;

    public OAuthSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        OAuth2User user = token.getPrincipal() ;

        System.out.println("OAuth 2 User ==> " + user);


        UserSignInResponseDTO res =
                userService.handleOAuth2UserLogIn(
                        user,
                        token.getAuthorizedClientRegistrationId()
                );

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
                """
                {
                  "message":"OAuth Login Success",
                  "jwtToken":"%s",
                  "userId":"%s"
                }
                """.formatted(
                        res.getJwtToken(),
                        res.getUserId()
                )
        );

        response.getWriter().flush();

    }
}
