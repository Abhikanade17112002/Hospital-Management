package com.hospitalmanagement.services.rolesandpermissionservice.impl;

import com.hospitalmanagement.dtos.rolesandpermissionsdtos.GetRolesAndPermissionsRequestDTO;
import com.hospitalmanagement.dtos.rolesandpermissionsdtos.GetRolesAndPermissionsResponseDTO;
import com.hospitalmanagement.entities.Role;
import com.hospitalmanagement.repositories.RoleRepository;
import com.hospitalmanagement.services.rolesandpermissionservice.RoleAndPermissionService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleAndPermissionServiceImpl implements RoleAndPermissionService {
    private final RoleRepository roleRepository ;
    private final ModelMapper modelMapper ;

    public RoleAndPermissionServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GetRolesAndPermissionsResponseDTO> getRolesAndPermissions() {
        List<Role> roles =  roleRepository.findAll() ;
        return roles.stream().map((role)->modelMapper.map(role, GetRolesAndPermissionsResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public GetRolesAndPermissionsResponseDTO getRolesAndPermissionsById(String roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(()-> new EntityNotFoundException()) ;
        return modelMapper.map(role, GetRolesAndPermissionsResponseDTO.class);
    }

}
