package com.hospitalmanagement.entities;


import com.hospitalmanagement.enums.BloodGroup;
import com.hospitalmanagement.enums.Gender;
import com.hospitalmanagement.enums.OAuthProviderType;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table( name = "application_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private String userId  ;

    private String firstName ;

    private String lastName ;

    private String emailId ;

    private String userName ;

    private String password ;

    private String oAuthProvideId ;

    @Enumerated(EnumType.STRING)
    private OAuthProviderType oAuthProviderType ;

    @Enumerated( EnumType.STRING )
    private Gender gender ;

    private LocalDate dateOfBirth ;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup ;


    public User(String userId, String firstName, String lastName, String emailId,  String password, String oAuthProvideId, OAuthProviderType oAuthProviderType, Gender gender, LocalDate dateOfBirth, BloodGroup bloodGroup) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.userName = this.firstName+this.lastName+"@"+(String.valueOf((int)(Math.random()*9999)));
        this.password = password;
        this.oAuthProvideId = oAuthProvideId;
        this.oAuthProviderType = oAuthProviderType;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getoAuthProvideId() {
        return oAuthProvideId;
    }

    public void setoAuthProvideId(String oAuthProvideId) {
        this.oAuthProvideId = oAuthProvideId;
    }

    public OAuthProviderType getoAuthProviderType() {
        return oAuthProviderType;
    }

    public void setoAuthProviderType(OAuthProviderType oAuthProviderType) {
        this.oAuthProviderType = oAuthProviderType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", oAuthProvideId='" + oAuthProvideId + '\'' +
                ", oAuthProviderType=" + oAuthProviderType +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", bloodGroup=" + bloodGroup +
                '}';
    }
}
