package com.deepak.blog.servicesImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.deepak.blog.entities.Role;
import com.deepak.blog.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.deepak.blog.entities.User;
import com.deepak.blog.exceptions.ResourceNotFoundException;
import com.deepak.blog.dto.UserDto;
import com.deepak.blog.response.UserResponse;
import com.deepak.blog.repositories.UserRepository;
import com.deepak.blog.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelmapper;

	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public User createUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto,user);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setUsername(userDto.getUsername() !=null ? userDto.getUsername() : user.getUsername());
		user.setEmail(userDto.getEmail() != null ? userDto.getEmail() : user.getEmail());
		user.setPassword(userDto.getPassword() != null ? userDto.getPassword() : user.getPassword());
		user.setAbout(userDto.getAbout() != null ? userDto.getAbout() : user.getAbout());
		User saveduser = this.userRepository.save(user);
		return this.modelmapper.map(saveduser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.modelmapper.map(user, UserDto.class);
	}

	@Override
	public UserResponse getAllUser(Integer pageNumber ,Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> userPage = this.userRepository.findAll(pageable);
		List<User> userList = userPage.getContent();
		List<UserDto> userDto = userList.stream().map(list -> this.modelmapper.map(list, UserDto.class)).collect(Collectors.toList());
		UserResponse userResponse = new UserResponse();
		userResponse.setContent(userDto);
		userResponse.setPageNumber(userPage.getNumber());
		userResponse.setPageSize(userPage.getSize());
		userResponse.setTotalElements(userPage.getTotalElements());
		userResponse.setTotalPages(userPage.getTotalPages());
		userResponse.setLastPage(userPage.isLast());
		return userResponse;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepository.delete(user);
	}
}
