package com.hospitalmanagement.services.rolesandpermissionservice;

import com.hospitalmanagement.dtos.rolesandpermissionsdtos.GetRolesAndPermissionsRequestDTO;
import com.hospitalmanagement.dtos.rolesandpermissionsdtos.GetRolesAndPermissionsResponseDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface RoleAndPermissionService {
    List<GetRolesAndPermissionsResponseDTO> getRolesAndPermissions();
    GetRolesAndPermissionsResponseDTO getRolesAndPermissionsById(String roleId);

}
