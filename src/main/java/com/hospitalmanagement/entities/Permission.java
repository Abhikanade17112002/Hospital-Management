package com.hospitalmanagement.entities;


import com.hospitalmanagement.enums.PermissionType;
import jakarta.persistence.*;

@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String permissionId ;

    @Enumerated(EnumType.STRING)
    private PermissionType permissionType ;

    public Permission() {
    }

    public Permission(String permissionId, PermissionType permissionType) {
        this.permissionId = permissionId;
        this.permissionType = permissionType;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId='" + permissionId + '\'' +
                ", permissionType=" + permissionType +
                '}';
    }
}
