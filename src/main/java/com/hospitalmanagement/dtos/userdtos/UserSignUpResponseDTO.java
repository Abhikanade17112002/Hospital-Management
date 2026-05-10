package com.hospitalmanagement.dtos.userdtos;

public class UserSignUpResponseDTO {

    private String userId ;
    private String userName ;

    public UserSignUpResponseDTO() {
    }

    public UserSignUpResponseDTO(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
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
    public String toString() {
        return "UserSignUpResponseDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
