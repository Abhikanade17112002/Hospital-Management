package com.hospitalmanagement.dtos.rolesandpermissionsdtos;

import com.hospitalmanagement.entities.Permission;
import com.hospitalmanagement.enums.RoleType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class GetRolesAndPermissionsResponseDTO {
    private String roleId ;
    private RoleType roleType ;
    private List<Permission> permissionList = new ArrayList<>() ;

    public GetRolesAndPermissionsResponseDTO() {
    }

    public GetRolesAndPermissionsResponseDTO(String roleId, RoleType roleType, List<Permission> permissionList) {
        this.roleId = roleId;
        this.roleType = roleType;
        this.permissionList = permissionList;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "GetRolesAndPermissionsResponseDTO{" +
                "roleId='" + roleId + '\'' +
                ", roleType=" + roleType +
                ", permissionList=" + permissionList +
                '}';
    }
}
