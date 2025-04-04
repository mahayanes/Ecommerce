package com.natixis.ecommerce.service.impl;

import com.natixis.ecommerce.dto.request.UserRequestDTO;
import com.natixis.ecommerce.dto.response.UserResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.InvalidEmailException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.mapper.UserMapper;
import com.natixis.ecommerce.model.Role;
import com.natixis.ecommerce.model.User;
import com.natixis.ecommerce.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public interface UserService {
    List<UserResponseDTO> getAllUsers();

    boolean checkUserExists(String username, String email);

    UserResponseDTO getUserById(Long userId);

     UserResponseDTO createUser(UserRequestDTO userRequestDTO);

     UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO);

     void deleteUser(Long userId);

     boolean isValidEmail(String email);

     User getCurrentUser();
}
