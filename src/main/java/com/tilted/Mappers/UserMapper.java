package com.tilted.Mappers;

import com.tilted.Models.User;
import com.tilted.Models.UserDTO;

public final class UserMapper {
    public static UserDTO ToDTO(User user) {
        var userDto = new UserDTO();
        userDto.Id = user.getId();
        userDto.Login = user.getLogin();
        userDto.Password = user.getPassword();
        return userDto;
    }

    public static User ToModel(UserDTO userDTO) {
        var user = new User();
        user.setId(userDTO.Id);
        user.setLogin(userDTO.Login);
        user.setPassword(userDTO.Password);
        return user;
    }

}
