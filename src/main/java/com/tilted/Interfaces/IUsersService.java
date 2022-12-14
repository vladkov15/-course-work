package com.tilted.Interfaces;


import com.tilted.Models.UserDTO;

import java.util.List;

public interface IUsersService {
    UserDTO Create(UserDTO user);
    List<UserDTO> GetAll();
    boolean GetById(String login, String password);

    boolean GetById(String login);

    boolean DeleteById(int id);
}
