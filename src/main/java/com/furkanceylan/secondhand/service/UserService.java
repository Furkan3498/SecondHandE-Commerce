package com.furkanceylan.secondhand.service;

import com.furkanceylan.secondhand.dto.CreateUserRequest;
import com.furkanceylan.secondhand.dto.UserDto;
import com.furkanceylan.secondhand.dto.UserDtoConverter;
import com.furkanceylan.secondhand.exceptions.UserNotFoundException;
import com.furkanceylan.secondhand.model.User;
import com.furkanceylan.secondhand.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(f -> userDtoConverter.convert(f)).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User couldn't be found by following id : " + id));

        return userDtoConverter.convert(user);
    }

    public UserDto createdUser(CreateUserRequest createUserRequest) {
        User user = new User(createUserRequest.getMail(), createUserRequest.getFirstName(), createUserRequest.getMiddleName(), createUserRequest.getLastName());
    }
}
