package com.furkanceylan.secondhand.service;


import com.furkanceylan.secondhand.dto.CreateUserDetailsRequest;
import com.furkanceylan.secondhand.dto.UpdateUserDetailsRequest;
import com.furkanceylan.secondhand.dto.UserDetailsDto;
import com.furkanceylan.secondhand.dto.UserDetailsDtoConverter;
import com.furkanceylan.secondhand.exceptions.UserDetailsNotFoundException;
import com.furkanceylan.secondhand.model.UserDetails;
import com.furkanceylan.secondhand.model.Users;
import com.furkanceylan.secondhand.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserService userService;
    private final UserDetailsDtoConverter userDetailsDtoConverter;

    public UserDetailsService(UserDetailsRepository userDetailsRepository, UserService userService, UserDetailsDtoConverter userDetailsDtoConverter) {
        this.userDetailsRepository = userDetailsRepository;
        this.userService = userService;
        this.userDetailsDtoConverter = userDetailsDtoConverter;
    }



    public UserDetailsDto createUserDetails(final CreateUserDetailsRequest request){

        Users users = userService.findUserById(request.getUserId());
        //if user couldnt found this process throw user service bcs this service not work about user
        //eğer user bulunamazsa, bulunamama durumundaki işleyişi user servise verdik çünkü o buranın işi değil

        UserDetails userDetails = new UserDetails(request.getPhoneNumber(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                request.getPostCode(),
                users);


        return userDetailsDtoConverter.convert(userDetailsRepository.save(userDetails));
    }
    public UserDetailsDto updateUserDetails(final Long userDetailsId, final UpdateUserDetailsRequest request){

        UserDetails userDetails = findUserDetailsById(userDetailsId);

        UserDetails updateUserDetails = new UserDetails(
                userDetails.getId(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                request.getPostCode(),
                userDetails.getUser()
               );


        return userDetailsDtoConverter.convert(userDetailsRepository.save(updateUserDetails));
    }


    private UserDetails findUserDetailsById(Long userDetailsId){

        return userDetailsRepository.findById(userDetailsId).orElseThrow(() ->
                new UserDetailsNotFoundException("User details couldn't be found by following id : " + userDetailsId));

    }






























































































}
