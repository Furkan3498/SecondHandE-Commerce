package com.furkanceylan.secondhand.dto;

import com.furkanceylan.secondhand.model.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    private final UserDetailsDtoConverter userDetailsDtoConverter;

    public UserDtoConverter(UserDetailsDtoConverter userDetailsDtoConverter) {
        this.userDetailsDtoConverter = userDetailsDtoConverter;
    }

    public  UserDto convert(Users user){

        UserDto userDto = new UserDto();
        userDto.setMail(user.getMail());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setUserDetailsDtos(userDetailsDtoConverter.convert(new ArrayList<>(user.getUserDetailsSet())));

        return userDto;

    }
    public List<UserDto> convert (List<Users> fromList){

        return fromList.stream().map(this::convert).collect(Collectors.toList());
    }
}
