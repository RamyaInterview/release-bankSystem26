package com.sample.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sample.app.dto.UserDto;
import com.sample.app.entity.User;
import com.sample.app.entity.UserTest;
import com.sample.app.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

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

		// Mock UserRepository
		when(userRepository.save(any(User.class))).thenReturn(user);
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(userRepository.findById(2L)).thenReturn(Optional.empty());
	}

	@Test
	public void testCreateUser() {
		User createdUser = userService.createUser(userDto);

		assertNotNull(createdUser.getId());
		assertEquals(userDto.getName(), createdUser.getName());

		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	public void testGetUserById() {
		User retrievedUser = userService.getUserById(user.getId());

		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(user.getName(), retrievedUser.getName());

		verify(userRepository, times(1)).findById(user.getId());
	}

	@Test(expected = RuntimeException.class)
	public void testGetUserById_UserNotFound() {
		userService.getUserById(2L); // User with ID 2 does not exist
	}

	@Test(expected = RuntimeException.class)
	public void testGetUserById_UserNotFound_WithNoSuchElementException() {
		when(userRepository.findById(2L)).thenReturn(Optional.empty());

		userService.getUserById(2L); // User with ID 2 does not exist
	}
}
