package com.furkanceylan.secondhand.service;


import com.furkanceylan.secondhand.dto.CreateUserDetailsRequest;
import com.furkanceylan.secondhand.dto.UserDetailsDto;
import com.furkanceylan.secondhand.dto.UserDetailsDtoConverter;
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



    public UserDetailsDto createUser(final CreateUserDetailsRequest request){

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

}
