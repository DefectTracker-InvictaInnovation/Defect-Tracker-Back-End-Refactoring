//package com.sgic.internal.product.controller;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sgic.internal.product.controller.dto.RoleDTO;
//import com.sgic.internal.product.controller.dto.mapper.RoleMapper;
//import com.sgic.internal.product.entities.Role;
//import com.sgic.internal.product.services.RoleServices;
//
//@CrossOrigin(origins = "*",allowedHeaders = "*")
//@RestController
//public class RoleController {
//	
//	@Autowired
//	private RoleMapper roleMapper;
//	
//	@Autowired
//	private RoleServices roleServices;
//	
//	private static Logger logger = LogManager.getLogger(RoleMapper.class);
//
//	// Get All Role
//	@GetMapping("/Roles")
//	public List<RoleDTO> getAllRole() {
//		logger.info("Controller -> Data Retrieved Successfull");
//		return roleMapper.getAllRole();
//	}
//
//	 //Get Role By Id
//	@GetMapping("/Role/{roleId}")
//	public RoleDTO getPrivilegeById(@PathVariable(name = "roleId") Long roleId) {
//		logger.info("Controller -> Data Retrieved Successfull");
//		return roleMapper.getRoleById(roleId);
//	}
//	
//
//	// Save Role
//	@PostMapping("/Role")
//	public ResponseEntity<String> saveRole(@Valid @RequestBody RoleDTO roleDTO) {
//		if (roleMapper.saveRole(roleDTO) != null) {
//			logger.info("Role Controller -> Role Created Successful");
//			return new ResponseEntity<>("Role added succesfully", HttpStatus.OK);
//		}
//		logger.info("Role Controller -> Role creation FAILED!!!");
//		return new ResponseEntity<>("SAVE FAILED!", HttpStatus.BAD_REQUEST);
//	}
//
//	// Update Role
//	@PutMapping("/Role")
//	public ResponseEntity<String> updateRole(@RequestBody RoleDTO roleDTO) {
//		logger.info("Role Controller -> Role Updated Successful");
//		if (roleMapper.updateRole(roleDTO) != null) {
//			return new ResponseEntity<>("Sucessfully Updated Role", HttpStatus.OK);
//		}
//		logger.info("Role Controller -> Role Updated Failed!!!");
//		return new ResponseEntity<>("Update FAILED!!!", HttpStatus.BAD_REQUEST);
//	}
//
//	 //Delete Role
//	@DeleteMapping("/Role/{privilageId}")
//	public ResponseEntity<String> deletePrivilege(@PathVariable(name = "roleId") Long roleId) {
//		System.out.print(roleId);
//		if (roleMapper.getRoleById(roleId) != null) {
//			if (roleMapper.deleteRoleById(roleId) == null) {
//				logger.info("Role Controller -> Role Deleted Successful");
//				return new ResponseEntity<>("Role Sucessfully deleted", HttpStatus.OK);
//			}
//		} else {
//			logger.info("Role Controller -> Role Id Not Found");
//			return new ResponseEntity<>("Role Id Not FOUND!!!", HttpStatus.BAD_REQUEST);
//		}
//		logger.info("Role Controller -> Role Deleted Failed!!!");
//		return new ResponseEntity<>("Delete FAILED!!!", HttpStatus.BAD_REQUEST);
//	}
//	
////	@GetMapping("/getById/{roleId}")
////	public Role getRole(@PathVariable(name="roleId") Long roleId) {
////		return roleServices.getRoleById(roleId);
////	}
//	
////	@GetMapping("/getById/{roleId}") 
////	public ResponseEntity<Role> getEmployeeById(@PathVariable(name = "roleId") Long roleId) {
////		return new ResponseEntity<>(roleServices.getRoleById(roleId), HttpStatus.OK);
////	}
//	
//}
