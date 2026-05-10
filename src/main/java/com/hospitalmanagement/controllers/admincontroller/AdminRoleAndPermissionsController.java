package com.hospitalmanagement.controllers.admincontroller;


import com.hospitalmanagement.dtos.rolesandpermissionsdtos.GetRolesAndPermissionsRequestDTO;
import com.hospitalmanagement.dtos.rolesandpermissionsdtos.GetRolesAndPermissionsResponseDTO;
import com.hospitalmanagement.services.rolesandpermissionservice.RoleAndPermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/rolesandpermissions")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminRoleAndPermissionsController {
    private final RoleAndPermissionService roleAndPermissionService ;

    public AdminRoleAndPermissionsController(RoleAndPermissionService roleAndPermissionService) {
        this.roleAndPermissionService = roleAndPermissionService;
    }
    @GetMapping("/")
    public ResponseEntity<List<GetRolesAndPermissionsResponseDTO>> getRolesAndPermissions(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(roleAndPermissionService.getRolesAndPermissions());
    }
    @GetMapping("/{roleId}")
    public ResponseEntity<GetRolesAndPermissionsResponseDTO> getRolesAndPermissionsById(@PathVariable(name = "roleId") String roleId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(roleAndPermissionService.getRolesAndPermissionsById(roleId));
    }
}
