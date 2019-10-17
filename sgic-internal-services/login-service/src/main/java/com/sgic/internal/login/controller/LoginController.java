package com.sgic.internal.login.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.internal.login.entities.Role;
import com.sgic.internal.login.entities.RoleName;
import com.sgic.internal.login.entities.User;
import com.sgic.internal.login.payload.UserProfile;
import com.sgic.internal.login.repositories.RoleRepository;
import com.sgic.internal.login.repositories.UserRepository;
import com.sgic.internal.login.request.LoginForm;
import com.sgic.internal.login.request.SignUpForm;
import com.sgic.internal.login.response.JwtResponse;
import com.sgic.internal.login.response.ResponseMessage;
import com.sgic.internal.login.securityjwt.JwtProvider;
import com.sgic.internal.login.services.CurrentUser;
import com.sgic.internal.login.servicesimpl.UserPrinciple;
import com.sgic.internal.login.servicesimpl.UserSummary;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
	

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), userDetails.isEnabled()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser( @RequestBody SignUpForm signUpRequest) {
		System.out.println("fffffffffffffffffffffffffffffffffffffff :" + signUpRequest.getEmail());
		
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(),signUpRequest.getLastname(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()) );

	String strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		
			switch (strRoles) {
			case "Admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: admin Role not find."));
				roles.add(adminRole);

				break;
			case "PM":
				Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: pm Role not find."));
				roles.add(pmRole);

				break;
			case "QA":
				Role qaRole = roleRepository.findByName(RoleName.ROLE_QA)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: qa Role not find."));
				roles.add(qaRole);

				break;
			case "Developer":
				Role devrole = roleRepository.findByName(RoleName.ROLE_DEVELOPER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: developer Role not find."));
				roles.add(devrole);

				break;
			case "HR":
				Role hrrole = roleRepository.findByName(RoleName.ROLE_HR)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: hr Role not find."));
				roles.add(hrrole);

				break;
			case "ProductAdmin":
				Role productrole = roleRepository.findByName(RoleName.ROLE_PRODUCT_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause:  productadmin Role not find."));
				roles.add(productrole);

				break;
			case "TecLead":
				Role techrole = roleRepository.findByName(RoleName.ROLE_TECH_LEAD)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause:  techlead Role not find."));
				roles.add(techrole);

				break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_QA)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}

		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

	@GetMapping("/user/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrinciple currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }
	
	@GetMapping("/user/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserSummary getCurrentAdmin(@CurrentUser UserPrinciple currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
				currentUser.getName());
		return userSummary;
	}	
	
	 @GetMapping("/users/{username}")
	    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
	    User user = userRepository.findByUsername(username);
	                
	        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(),user.getName(),user.getLastname(),user.getEmail(),user.getRoles());

	        return userProfile;
	    }
}