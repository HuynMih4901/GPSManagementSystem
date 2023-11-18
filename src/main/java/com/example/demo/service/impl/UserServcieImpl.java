package com.example.demo.service.impl;

import com.example.demo.dto.user.UserLoginRequestDTO;
import com.example.demo.dto.user.UserResponseDTO;
import com.example.demo.dto.user.UserSignUpRequestDTO;
import com.example.demo.enums.RoleEnum;
import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.Customer;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.CustomerService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.utils.GPSUtils;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServcieImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleService roleService;
  private final CustomerService customerService;

  @Override
  public void signUp(UserSignUpRequestDTO request) {
    // check exist user
    Optional<User> user = userRepository.findByUsername(request.getUsername());
    if (user.isPresent()) {
      throw new GPSException(ExceptionUtils.E_USERNAME_EXISTED);
    }
    // check exist role
    Set<String> roles =
        request.getRoles().stream().map(RoleEnum::getCode).collect(Collectors.toSet());
    Set<Role> validRole = roleService.getAllRoles(roles);
    if (validRole.isEmpty()) {
      throw new GPSException(ExceptionUtils.E_ROLE_NOT_EXISTED);
    }
    // encode password
    String encodedPassword = GPSUtils.encodePassword(request.getPassword(), request.getUsername());
    if (encodedPassword == null) {
      throw new GPSException(ExceptionUtils.E_SIGN_UP_ERROR);
    }

    // create new user customer
    Customer customer = customerService.createCustomer(request.getCustomer());
    User newUser =
        User.builder()
            .username(request.getUsername())
            .password(encodedPassword)
            .roles(validRole)
            .customer(customer)
            .build();
    userRepository.save(newUser);
  }

  @Override
  public UserResponseDTO login(UserLoginRequestDTO request) {
    String encodePassword = GPSUtils.encodePassword(request.getPassword(), request.getUsername());
    User user =
        userRepository
            .findByUsernameAndPassword(request.getUsername(), encodePassword)
            .orElseThrow(() -> new GPSException(ExceptionUtils.E_USERNAME_PASSWORD_NOT_VALID));

    String accessToken = SecurityUtils.generateToken(user);
    Set<Role> roles = user.getRoles();
    String userCode = null;
    if (user.getAdminitrator() == null) {
      userCode = user.getCustomer().getCode();
    } else {
      userCode = user.getAdminitrator().getCode();
    }
    return new UserResponseDTO(accessToken, request.getUsername(), roles, userCode);
  }

  @Override
  public User getUser(int id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
  }
}
