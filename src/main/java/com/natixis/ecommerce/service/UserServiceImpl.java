package com.natixis.ecommerce.service;

import com.natixis.ecommerce.dto.request.UserRequestDTO;
import com.natixis.ecommerce.dto.response.UserResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.InvalidEmailException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.mapper.UserMapper;
import com.natixis.ecommerce.model.Role;
import com.natixis.ecommerce.model.User;
import com.natixis.ecommerce.repository.UserRepository;
import com.natixis.ecommerce.service.impl.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDTO> getAllUsers() {

        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.mapToUserResponseDTO(users)  ;
    }
    public boolean checkUserExists(String username, String email) {
        return userRepository.existsByUsername(username) || userRepository.existsByEmail(email);
    }

    public UserResponseDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
        return UserMapper.INSTANCE.mapToUserResponseDTO(user);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (checkUserExists(userRequestDTO.getUsername(), userRequestDTO.getEmail())) {
            throw new AlreadyExistsException("User with username " + userRequestDTO.getUsername() + " or email "+userRequestDTO.getEmail() +" already exists.");
        }
        if (!isValidEmail(userRequestDTO.getEmail())){
            throw new InvalidEmailException("Invalid email format: " + userRequestDTO.getEmail());
        }
        User user = UserMapper.INSTANCE.mapToUser(userRequestDTO);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setUsername(userRequestDTO.getUsername());
        user.setCreationDateTime(new Date());
        user = userRepository.save(user);
        return UserMapper.INSTANCE.mapToUserResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        if (!isValidEmail(userRequestDTO.getEmail())){
            throw new InvalidEmailException("Invalid email format: " + userRequestDTO.getEmail());
        }

         existingUser.setName(userRequestDTO.getName());
         existingUser.setEmail(userRequestDTO.getEmail());
         existingUser.setRole(Role.valueOf(userRequestDTO.getRole()));
         existingUser.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.INSTANCE.mapToUserResponseDTO(updatedUser);
    }
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
        userRepository.delete(existingUser);
    }

    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Optional<User> user = userRepository.findByUsername(authentication.getName());
        return user.orElse(null);
    }
}
