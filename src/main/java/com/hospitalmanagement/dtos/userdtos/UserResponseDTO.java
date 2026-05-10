package com.hospitalmanagement.dtos.userdtos;

import com.hospitalmanagement.entities.Role;
import com.hospitalmanagement.enums.RoleType;

import java.util.List;

public class UserResponseDTO {

    private String userId;
    private String userName;
    private String emailId;
    private boolean isActive;
    private List<Role> roles;
    private boolean isProfileComplete ;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String userId, String userName, String emailId, boolean isActive, List<Role> roles, boolean isProfileComplete) {
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
        this.isActive = isActive;
        this.roles = roles;
        this.isProfileComplete = isProfileComplete;
    }

    public boolean isProfileComplete() {
        return isProfileComplete;
    }

    public void setProfileComplete(boolean profileComplete) {
        isProfileComplete = profileComplete;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public boolean isActive() {
        return isActive;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", isActive=" + isActive +
                ", roles=" + roles +
                ", isProfileComplete=" + isProfileComplete +
                '}';
    }
}