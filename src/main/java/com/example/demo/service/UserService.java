package com.example.demo.service;

import com.example.demo.dto.user.UserLoginRequestDTO;
import com.example.demo.dto.user.UserResponseDTO;
import com.example.demo.dto.user.UserSignUpRequestDTO;
import com.example.demo.model.User;

public interface UserService {

  void signUp(UserSignUpRequestDTO dto);

  UserResponseDTO login(UserLoginRequestDTO dto);

  User getUser(int id);
}
