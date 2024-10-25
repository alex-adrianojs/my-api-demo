package br.com.alexadrianodev.myapidemo.services;

import br.com.alexadrianodev.myapidemo.domain.User;

public interface UserService {

    User findById(Integer id);
}
