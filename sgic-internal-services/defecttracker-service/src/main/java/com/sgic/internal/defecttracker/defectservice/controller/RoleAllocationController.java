package com.sgic.internal.defecttracker.defectservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.common.api.enums.RestApiResponseStatus;
import com.sgic.common.api.response.ApiResponse;
import com.sgic.internal.defecttracker.defectservice.controller.dto.RoleAllocationDto;
import com.sgic.internal.defecttracker.defectservice.controller.dto.mapper.RoleAllocationDtoMapper;
import com.sgic.internal.defecttracker.defectservice.entities.RoleAllocation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleAllocationController {

	@Autowired
	private RoleAllocationDtoMapper roleAllocationDtoMapper;

	//create role allocations
	@PostMapping(value = "/createroleallocation")
	public ResponseEntity<Object> createRoleAllocation(@Valid @RequestBody RoleAllocationDto roleAllocationDto) {
		roleAllocationDtoMapper.saveRoleAllocationforMapper(roleAllocationDto);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.OK), HttpStatus.OK);
	}

	//get all roles
	@GetMapping(value = "/GetAllRole")
	public ResponseEntity<List<RoleAllocationDto>> listroleInfo() {
		return new ResponseEntity<>(roleAllocationDtoMapper.getllRoleAllocationForMapper(), HttpStatus.OK);

	}

	//get role for the particular employee
	@PutMapping("updaterole/{empId}")
	public ResponseEntity<Long> updateRoleAllocation(@PathVariable("empId") Long empId,
			@RequestBody RoleAllocationDto roleAllocationDto) {
		if (roleAllocationDtoMapper.UpdateRoleAllocation(empId,roleAllocationDto) != null)
			;
		{
			return new ResponseEntity<Long>(HttpStatus.OK);
		}

	}

}
