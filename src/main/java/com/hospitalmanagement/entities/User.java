package com.hospitalmanagement.entities;

import com.hospitalmanagement.enums.OAuthProviderType;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "application_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String userName;
    private String password;
    private String oAuthProvideId;
    @Column(updatable = false, unique = true)
    private String emailId;
    @Enumerated(EnumType.STRING)
    private OAuthProviderType oAuthProviderType;
    private boolean isProfileComplete = false;
    private boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(String userId, String userName, String password, String oAuthProvideId, String emailId, OAuthProviderType oAuthProviderType, boolean isProfileComplete, boolean isActive, List<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.oAuthProvideId = oAuthProvideId;
        this.emailId = emailId;
        this.oAuthProviderType = oAuthProviderType;
        this.isProfileComplete = isProfileComplete;
        this.isActive = isActive;
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {

            // Add ROLE_
            authorities.add(new SimpleGrantedAuthority(role.getRoleType().name()));

            // Add permissions
            role.getPermissionList().forEach(permission ->
                    authorities.add(
                            new SimpleGrantedAuthority(permission.getPermissionType().getPermission())
                    )
            );
        }

        return authorities;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isActive() {
        return isActive;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isProfileComplete() {
        return isProfileComplete;
    }

    public void setProfileComplete(boolean profileComplete) {
        isProfileComplete = profileComplete;
    }



    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", oAuthProvideId='" + oAuthProvideId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", oAuthProviderType=" + oAuthProviderType +
                ", isProfileComplete=" + isProfileComplete +
                ", isActive=" + isActive +
                ", roles=" + roles +
                '}';
    }
}
