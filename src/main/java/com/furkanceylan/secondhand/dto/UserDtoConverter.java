package com.furkanceylan.secondhand.dto;

import com.furkanceylan.secondhand.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public  UserDto convert(User user){

        UserDto userDto = new UserDto();
        userDto.setMail(user.getMail());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        return userDto;

    }
}
