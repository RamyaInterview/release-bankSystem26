package com.sample.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sample.app.dto.UserDto;
import com.sample.app.entity.User;
import com.sample.app.entity.UserTest;
import com.sample.app.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private UserDto userDto;
	private User user;

	@Before
	public void setup() {
		// Create a sample userDto for testing
		userDto = new UserDto();
		userDto.setName("John Doe");

		// Create a sample user for testing
		user = new User();
		user.setId(1L);
		user.setName("John Doe");
	}

	@Test
	public void testCreateUser() {
		when(userService.createUser(userDto)).thenReturn(user);

		User createdUser = userController.createUser(userDto);

		assertEquals(user.getId(), createdUser.getId());
		assertEquals(user.getName(), createdUser.getName());

		verify(userService, times(1)).createUser(userDto);
	}

	@Test
	public void testGetUserById() {
		Long userId = 1L;
		when(userService.getUserById(userId)).thenReturn(user);

		User retrievedUser = userController.getUserById(userId);

		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(user.getName(), retrievedUser.getName());

		verify(userService, times(1)).getUserById(userId);
	}
}
