package com.example.rmsoft.domain.user.mapper;

import com.example.rmsoft.domain.user.dto.UserDto;
import com.example.rmsoft.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDtoToUser(UserDto userPostDto);
    UserDto userToUserDto(User user);
}
