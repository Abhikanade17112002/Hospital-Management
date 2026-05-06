package com.hospitalmanagement.dtos.userdtos;

import com.hospitalmanagement.enums.RoleType;

import java.util.List;

public class AssignRoleRequestDTO {
    private String userId ;
    private List<RoleType> roleTypes ;


    public AssignRoleRequestDTO() {
    }

    public AssignRoleRequestDTO(String userId, List<RoleType> roleTypes) {
        this.userId = userId;
        this.roleTypes = roleTypes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<RoleType> getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(List<RoleType> roleTypes) {
        this.roleTypes = roleTypes;
    }

    @Override
    public String toString() {
        return "AssignRoleRequestDTO{" +
                "userId='" + userId + '\'' +
                ", roleTypes=" + roleTypes +
                '}';
    }
}
