package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.UserDto;
import com.sample.app.entity.User;
import com.sample.app.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/users")
@Api(tags = "User Management")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Create a user")
	@PostMapping
	public User createUser(@ApiParam(value = "User object") @RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}

	@ApiOperation(value = "Get user by ID")
	@GetMapping("/{userId}")
	public User getUserById(@ApiParam(value = "User ID") @PathVariable Long userId) {
		return userService.getUserById(userId);
	}

}
