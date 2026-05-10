package com.hospitalmanagement.entities;

import com.hospitalmanagement.enums.RoleType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private String roleId ;
    @Enumerated(EnumType.STRING)
    private RoleType roleType ;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissionList = new ArrayList<>() ;

    public Role() {
    }

    public Role(String roleId, RoleType roleType, List<Permission> permissionList) {
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
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleType=" + roleType +
                ", permissionList=" + permissionList +
                '}';
    }
}
