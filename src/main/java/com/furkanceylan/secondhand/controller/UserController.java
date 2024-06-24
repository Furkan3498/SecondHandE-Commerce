package com.furkanceylan.secondhand.controller;


import com.furkanceylan.secondhand.dto.CreateUserRequest;
import com.furkanceylan.secondhand.dto.UpdateUserRequest;
import com.furkanceylan.secondhand.dto.UserDto;
import com.furkanceylan.secondhand.model.User;
import com.furkanceylan.secondhand.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest){
        return  ResponseEntity.ok(userService.createdUser(createUserRequest)); //201
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserRequest updateUserRequest){
        return  ResponseEntity.ok(userService.updateUser()); //202
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> passiveUser(@PathVariable("id") Long id ){
        userService.passiveUser(id); // passive account
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserHaveTo(@PathVariable("id") Long id){
       userService.deleteUserForHaveTo(id);

        return ResponseEntity.ok().build();
    }






























}
