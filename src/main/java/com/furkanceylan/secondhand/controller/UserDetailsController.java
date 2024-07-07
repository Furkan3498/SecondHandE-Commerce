package com.furkanceylan.secondhand.controller;


import com.furkanceylan.secondhand.dto.CreateUserDetailsRequest;
import com.furkanceylan.secondhand.dto.UpdateUserDetailsRequest;
import com.furkanceylan.secondhand.dto.UpdateUserRequest;
import com.furkanceylan.secondhand.dto.UserDetailsDto;
import com.furkanceylan.secondhand.service.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/userdetails")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsDto> createUserDetails(@RequestBody CreateUserDetailsRequest request){

        return ResponseEntity.ok(userDetailsService.createUserDetails(request));
    }


    //Post ile Put Mapping farkı ResponseStatusde 201 ve 202 geri dönüş değeri için yoksa ikisi icinde Post Kullanılabilir
    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsDto> updateUserDetails(@PathVariable Long id , @RequestBody UpdateUserDetailsRequest updateUserRequest){
        return ResponseEntity.ok(userDetailsService.updateUserDetails(id,updateUserRequest));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id){
        userDetailsService.deleteUserDetails(id);

        return  ResponseEntity.ok().build();
    }















}
