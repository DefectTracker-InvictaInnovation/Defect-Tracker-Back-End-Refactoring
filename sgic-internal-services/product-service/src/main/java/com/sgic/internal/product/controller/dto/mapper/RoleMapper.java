package com.sgic.internal.product.controller.dto.mapper;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sgic.internal.product.controller.dto.RoleDTO;
import com.sgic.internal.product.controller.dto.converter.RoleConverter;
import com.sgic.internal.product.entities.Role;
import com.sgic.internal.product.services.RoleServices;

public class RoleMapper {
	
	@Autowired
	private RoleConverter roleConverter;
	@Autowired
	private RoleServices roleServices;

	private static Logger logger = LogManager.getLogger(RoleDTO.class);

	// Get All Roles
	@SuppressWarnings("static-access")
	public List<RoleDTO> getAllRole() {
		logger.info("Privilege Mapper -> All Privilege Data Retrieved");
		List<Role> rolelist = roleServices.getAllRole();
		return roleConverter.RoleEntityToRoleDTOList(rolelist);
	}

	// Save Role
	@SuppressWarnings("static-access")
	public Role saveRole(RoleDTO roleDTO) {
		logger.info("Role Mapper -> Role Saved");
		return roleServices.saveRole(roleConverter.RoleDTOTORoleEntity(roleDTO));
	}

	// Update Role
	@SuppressWarnings("static-access")
	public Role updateRole(RoleDTO roleDTO) {
		logger.info("Role Mapper -> Role Updated ", roleDTO.getRoleName());
		return roleServices.updateRole(roleConverter.RoleDTOTORoleEntity(roleDTO));
	}

	// Delete Role
	public RoleDTO deleteRoleById(Long roleId) {
		logger.info("Role Mapper -> Role Deleted");
		roleServices.deleteRoleById(roleId);
		return null;
	}

	 //Get Role By Id
	@SuppressWarnings("static-access")
	public RoleDTO getRoleById(Long roleId) {
		logger.info("Role Mapper -> Role Id Found");
		Role role  = roleServices.getRoleById(roleId);
		return roleConverter.RoleEntityTORoleDTO(role);
	}

}
