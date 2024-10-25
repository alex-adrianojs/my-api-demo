package br.com.alexadrianodev.myapidemo.services.impl;


import br.com.alexadrianodev.myapidemo.domain.User;
import br.com.alexadrianodev.myapidemo.repositories.UserRepository;
import br.com.alexadrianodev.myapidemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }
}
