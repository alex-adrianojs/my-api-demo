package br.com.alexadrianodev.myapidemo.services;

import br.com.alexadrianodev.myapidemo.domain.User;

import java.util.List;


public interface UserService {

    User findById(Integer id);

    public List<User> findAll();
}
