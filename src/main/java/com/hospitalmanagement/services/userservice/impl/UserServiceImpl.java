package com.hospitalmanagement.services.userservice.impl;

import com.hospitalmanagement.dtos.doctordtos.GetDoctorResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import com.hospitalmanagement.dtos.userdtos.*;
import com.hospitalmanagement.entities.Doctor;
import com.hospitalmanagement.entities.Patient;
import com.hospitalmanagement.entities.Role;
import com.hospitalmanagement.entities.User;
import com.hospitalmanagement.enums.OAuthProviderType;
import com.hospitalmanagement.enums.RoleType;
import com.hospitalmanagement.repositories.DoctorRepository;
import com.hospitalmanagement.repositories.RoleRepository;
import com.hospitalmanagement.repositories.UserRepository;
import com.hospitalmanagement.repositories.patientrepository.PatientRepository;
import com.hospitalmanagement.services.userservice.UserService;
import com.hospitalmanagement.utility.JWTUtility;
import com.hospitalmanagement.utility.OAuthUtility;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder ;
    private final JWTUtility jwtUtility ;
    private final AuthenticationManager authenticationManager ;
    private final ModelMapper modelMapper ;
    private final RoleRepository roleRepository ;
    private final PatientRepository patientRepository ;
    private final DoctorRepository doctorRepository ;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtility jwtUtility, @Lazy AuthenticationManager authenticationManager, ModelMapper modelMapper, RoleRepository roleRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
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
        String fName = OAuthUtility.getFirstName(user,authorizedClientRegistrationId);
        String lName = OAuthUtility.getLastName(user,authorizedClientRegistrationId);
        String providerId = OAuthUtility.getProviderId(user,authorizedClientRegistrationId);
        String emailId = OAuthUtility.getUserEmail(user,authorizedClientRegistrationId);
        OAuthProviderType providerType = OAuthUtility.getProviderType(authorizedClientRegistrationId);
        Optional<User> existingUser =  userRepository.findByOAuthProvideIdAndOAuthProviderTypeAndEmailId(providerId,providerType,emailId) ;
        User savedUser = null ;
        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER) ;

        if( existingUser.isEmpty() ){
            User newUser = new User() ;
            newUser.setActive(true);
            newUser.setUserName(fName +lName + Math.round(Math.random()*9999 + 1));
            newUser.setRoles(List.of(role));
            newUser.setEmailId(emailId);
            newUser.setoAuthProvideId(providerId);
            newUser.setoAuthProviderType(providerType);
            savedUser = userRepository.save(newUser);



        }
        else if( userRepository.findByEmailId(emailId).isPresent() ){
            System.out.println("User With Email Id ==> " + emailId + " Already Exists ") ;
            savedUser = userRepository.findByEmailId(emailId).orElseThrow(()-> new EntityNotFoundException()) ;
        }

        UserSignInResponseDTO response = new UserSignInResponseDTO() ;
        response.setUserId(savedUser.getUserId());
        response.setJwtToken(jwtUtility.generateToken(savedUser));
        return response ;
    }

    @Override
    public UserSignUpResponseDTO userSignUp(UserSignUpRequestDTO userSignUpRequest) {
        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER) ;
        User newUser = new User() ;
        newUser.setActive(true);
        newUser.setUserName(userSignUpRequest.getUserName());
        newUser.setRoles(List.of(role));
        newUser.setPassword(passwordEncoder.encode( userSignUpRequest.getPassword() ) );
        newUser.setEmailId(userSignUpRequest.getEmailId());
        newUser.setoAuthProviderType(OAuthProviderType.EMAIL_ID);
        newUser.setoAuthProvideId("Email_Id");
        User savedUser = userRepository.save(newUser);
        return modelMapper.map(savedUser,UserSignUpResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getAllRegisteredUser() {
        return userRepository.findAll().stream()
                .map((user)->modelMapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AssignRoleResponseDTO assignRole(AssignRoleRequestDTO assignRoleRequestDTO) {
        User retrivedUser = userRepository.findById(assignRoleRequestDTO.getUserId()).orElseThrow(()-> new EntityNotFoundException("User With Id ==> " + assignRoleRequestDTO.getUserId() + " Not Found")) ;
        List<Role> roles = new ArrayList<>() ;
        for(RoleType role : assignRoleRequestDTO.getRoleTypes()){
          Role retrivedRole =  roleRepository.findByRoleType(role);
          roles.add(retrivedRole) ;
        }
        retrivedUser.getRoles().addAll(roles);
        User savedUser = userRepository.save(retrivedUser);
        return modelMapper.map(savedUser, AssignRoleResponseDTO.class);
    }

    @Override
    public UserResponseDTO getRegisteredUser(String userId) {
        User retrivedUser = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User With Id ==> " + userId + " Not Found "));
        return modelMapper.map(retrivedUser,UserResponseDTO.class);
    }

    @Override
    public Boolean deleteUserById(String userId) {
        if( patientRepository.existsById(userId) ){
            patientRepository.deleteById(userId);
        }
        if( doctorRepository.existsById(userId) ){
            doctorRepository.deleteById(userId);
        }
        if( userRepository.existsById(userId)){
            userRepository.deleteById(userId);
        }
        else {
            throw new EntityNotFoundException("User With User Id ==> " + userId + " Not Found " );
        }
        return true;
    }

    @Override
    public String toggleUserStatus(String userId) {
            User retrivedUser = userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User With User Id ==> " + userId + " Not Found " ));
            retrivedUser.setActive(!retrivedUser.isActive());
            userRepository.save(retrivedUser);
            return "Toggled Profile Status Of User With Id " + userId + " Succesfully ";
    }

    @Override
    public GetPatientResponseDTO onBoardPatientByUserId(String userId) {
        User retrivedUser = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User With User Id ==> " + userId + " Not Found"));
        Role patientRole = roleRepository.findByRoleType(RoleType.ROLE_PATIENT) ;
        retrivedUser.getRoles().add(patientRole);
        Patient patient = new Patient() ;
        patient.setUser(retrivedUser);
        patientRepository.save(patient);
        return modelMapper.map(patient, GetPatientResponseDTO.class) ;
    }

}
