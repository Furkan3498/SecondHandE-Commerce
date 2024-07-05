package com.furkanceylan.secondhand.dto;

import com.furkanceylan.secondhand.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public  UserDto convert(Users user){

        UserDto userDto = new UserDto();
        userDto.setMail(user.getMail());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        return userDto;

    }
}
