package br.com.alexadrianodev.myapidemo.services;

import br.com.alexadrianodev.myapidemo.domain.User;
import br.com.alexadrianodev.myapidemo.domain.dto.UserDTO;

import java.util.List;


public interface UserService {

    User findById(Integer id);
    public List<User> findAll();

    User create(UserDTO user);

    User update(UserDTO user);
}
