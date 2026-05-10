package com.hospitalmanagement.dtos.userdtos;

import jakarta.validation.constraints.NotBlank;

public class UserSignInRequestDTO {
    @NotBlank(message = "User Name Cannot Be Blank")
    private String userName ;
    @NotBlank(message = "Password Cannot Be Blank")
    private String password ;

    public UserSignInRequestDTO() {
    }

    public UserSignInRequestDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public @NotBlank(message = "Password Cannot Be Blank") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password Cannot Be Blank") String password) {
        this.password = password;
    }

    public @NotBlank(message = "User Name Cannot Be Blank") String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank(message = "User Name Cannot Be Blank") String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserSignInRequestDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
