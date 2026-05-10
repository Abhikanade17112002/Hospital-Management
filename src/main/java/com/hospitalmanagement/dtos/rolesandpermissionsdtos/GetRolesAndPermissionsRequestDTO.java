package com.hospitalmanagement.dtos.rolesandpermissionsdtos;

import com.hospitalmanagement.entities.Permission;
import com.hospitalmanagement.enums.RoleType;

import java.util.ArrayList;
import java.util.List;

public class GetRolesAndPermissionsRequestDTO {
    private RoleType roleType ;
    private List<Permission> permissionList = new ArrayList<>() ;

    public GetRolesAndPermissionsRequestDTO(RoleType roleType, List<Permission> permissionList) {
        this.roleType = roleType;
        this.permissionList = permissionList;
    }

    public GetRolesAndPermissionsRequestDTO() {
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
        return "GetRolesAndPermissionsRequestDTO{" +
                "roleType=" + roleType +
                ", permissionList=" + permissionList +
                '}';
    }
}
