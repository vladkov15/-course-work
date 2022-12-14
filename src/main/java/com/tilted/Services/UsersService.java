package com.tilted.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tilted.Interfaces.IUsersService;
import com.tilted.Mappers.UserMapper;
import com.tilted.Models.CheckUser;
import com.tilted.Models.UserDTO;
import com.tilted.Repositories.IUsersRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UsersService implements IUsersService {

    private final IUsersRepository usersRepository;

    public UsersService(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDTO Create(UserDTO user) {
        return UserMapper.ToDTO(usersRepository.save(UserMapper.ToModel(user)));
    }

    @Override
    public List<UserDTO> GetAll() {
        return usersRepository.findAll().stream().map(UserMapper::ToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean GetById(String login, String password) {
        return false;
    }

    @Override
    public boolean GetById(String login) {
        boolean response = true;
        Gson gson = new Gson();

        // JSON file to Java object
        CheckUser checkUser= gson.fromJson(login, CheckUser.class);
        var users = usersRepository.findAll().stream().map(UserMapper::ToDTO).collect(Collectors.toList());
        for (UserDTO user : users) {
            if (user.Login.equals(checkUser.login) && user.Password.equals(checkUser.password)) {
            }else {
                response = false;
            }
        }
        return response;
    }

    @Override
    public boolean DeleteById(int id) {
        return false;
    }
}
