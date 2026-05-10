package com.hospitalmanagement.dtos.userdtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserSignUpRequestDTO {
    @NotBlank(message = "User Name Cannot Be Blank")
    private String userName  ;
    @NotBlank(message = "Password Cannot Be Blank")
    private String password ;
    @Email(message = "EmailId Cannot Be Blank")
    private String emailId ;


    public UserSignUpRequestDTO() {
    }

    public UserSignUpRequestDTO(String userName, String password, String emailId) {
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "UserSignUpRequestDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
