package com.furkanceylan.secondhand.dto;

import com.furkanceylan.secondhand.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsDtoConverter {

    public UserDetailsDto convert(UserDetails userDetails){

        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setAddress(userDetails.getAddress());
        userDetailsDto.setCity(userDetails.getCity());
        userDetailsDto.setCountry(userDetails.getCountry());
        userDetailsDto.setPhoneNumber(userDetails.getPhoneNumber());
        userDetailsDto.setPostCode(userDetails.getPostCode());

        return userDetailsDto;
    }

    public List<UserDetailsDto> convert( List<UserDetails> userDetailsList){
        return userDetailsList.stream().map(f-> convert(f)).collect(Collectors.toList());
    }
}
