 package com.deepak.blog.controller;

import com.deepak.blog.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.blog.config.AppConstants;
import com.deepak.blog.response.ApiResponse;
import com.deepak.blog.dto.UserDto;
import com.deepak.blog.response.UserResponse;
import com.deepak.blog.services.IUserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private IUserService userService;

	//post 
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<User>(this.userService.createUser(userDto), HttpStatus.CREATED);
	}

	//put
//	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @RequestParam Integer userId) {
		return new ResponseEntity<UserDto>(this.userService.updateUser(userDto, userId), HttpStatus.OK);
	}

	//get all
	@GetMapping("/")
	public ResponseEntity<UserResponse> getAllUser(
			@RequestParam(value ="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value ="pageSize",defaultValue=AppConstants.PAGE_SIZE,required = false) Integer pageSize) {
		return new ResponseEntity<UserResponse>(this.userService.getAllUser(pageNumber,pageSize), HttpStatus.OK);

	}

	//get by Id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserByID(@PathVariable Integer userId) {
		return new ResponseEntity<UserDto>(this.userService.getUserById(userId), HttpStatus.OK);

	}

	//delete
	@DeleteMapping("/")
	public ResponseEntity<?> deleteUser(@RequestParam Integer userId) {
		this.userService.deleteUser(userId);
		return ResponseEntity.ok(new ApiResponse("User Deleted Successfully", true));
	}
}
