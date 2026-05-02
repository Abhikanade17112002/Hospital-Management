package com.hospitalmanagement.services.userservice.impl;

import com.hospitalmanagement.dtos.userdtos.UserSignInRequestDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignInResponseDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignUpRequestDTO;
import com.hospitalmanagement.dtos.userdtos.UserSignUpResponseDTO;
import com.hospitalmanagement.entities.User;
import com.hospitalmanagement.enums.OAuthProviderType;
import com.hospitalmanagement.repositories.UserRepository;
import com.hospitalmanagement.services.userservice.UserService;
import com.hospitalmanagement.utility.JWTUtility;
import com.hospitalmanagement.utility.OAuthUtility;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder ;
    private final JWTUtility jwtUtility ;
    private final AuthenticationManager authenticationManager ;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtility jwtUtility, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("User Name ==>" + username + " Not Found"));
    }

    @Override
    public UserSignInResponseDTO userSignIn(UserSignInRequestDTO userSignInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userSignInRequest.getUserName(),userSignInRequest.getPassword())
        ) ;

       User retrivedUser = (User)authentication.getPrincipal();

        if(retrivedUser != null){

            String generatedToken = jwtUtility.generateToken(retrivedUser);
            UserSignInResponseDTO response = new UserSignInResponseDTO(generatedToken, retrivedUser.getUserId());
            return response ;
        }
        else{
            System.out.println("User Password Is Incorrect");
        }
        return null;
    }

    @Override
    public UserSignInResponseDTO handleOAuth2UserLogIn(OAuth2User user, String authorizedClientRegistrationId) {

        // Step 1 Identify Provider Type And ID

        OAuthProviderType providerType = OAuthUtility.getProviderType(authorizedClientRegistrationId);
        String providerId = OAuthUtility.getProviderId(user,authorizedClientRegistrationId) ;


        System.out.println("providerType , providerId" + providerType + " " + providerId);

        User savedUser = (User) userRepository.findByOAuthProvideIdAndOAuthProviderType(providerId,providerType).orElse(null);
        String userEmailId = OAuthUtility.getUserEmail(user,authorizedClientRegistrationId);

        System.out.printf("retrivedUser , userEmailId ==>  %s %s \n" , savedUser,userEmailId);


        if( savedUser == null && userEmailId != null ){ // This Means No User

            String fName = OAuthUtility.getFirstName(user,authorizedClientRegistrationId);
            String lName = OAuthUtility.getFirstName(user,authorizedClientRegistrationId);
            savedUser = userSignUpForOAuth( fName,lName,userEmailId,providerId,providerType);
        } else if ( savedUser != null && !savedUser.getEmailId().equalsIgnoreCase(userEmailId)) {
            savedUser.setEmailId(userEmailId);
            savedUser = userRepository.save(savedUser);
        }
        else if ( userEmailId != null ){
            throw new BadCredentialsException("User With " + userEmailId + " Already Exists ") ;
        }

        UserSignInResponseDTO res = new UserSignInResponseDTO(jwtUtility.generateToken(savedUser),savedUser.getUserId()) ;
        return res;
    }

    public UserSignUpResponseDTO userSignUp(UserSignUpRequestDTO userSignUpRequest){
        User newUser = new User(
                null ,
                userSignUpRequest.getFirstName(),
                userSignUpRequest.getLastName(),
                userSignUpRequest.getEmailId(),
                passwordEncoder.encode(userSignUpRequest.getPassword()),
                null,
                null,
                userSignUpRequest.getGender(),
                userSignUpRequest.getDateOfBirth(),
                userSignUpRequest.getBloodGroup()
                ) ;
        User savedUser = userRepository.save(newUser);

        UserSignUpResponseDTO response = new UserSignUpResponseDTO(
                savedUser.getUserId(),
                savedUser.getUsername()
        );

        return response ;
    }

    public User userSignUpForOAuth(String firstName , String lastName , String emailId,String providerId,OAuthProviderType providerType){
        User newUser = new User() ;
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmailId(emailId);
        newUser.setUserName(firstName+lastName+"@"+String.valueOf((Math.random()*99999 + 1)));
        newUser.setoAuthProviderType(providerType);
        newUser.setoAuthProvideId(providerId);
        return userRepository.save(newUser);
    }
}
