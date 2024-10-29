package br.com.alexadrianodev.myapidemo.services.impl;


import br.com.alexadrianodev.myapidemo.domain.User;
import br.com.alexadrianodev.myapidemo.repositories.UserRepository;
import br.com.alexadrianodev.myapidemo.services.UserService;
import br.com.alexadrianodev.myapidemo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    public List<User> findAll(){
        return repository.findAll();
    }
}
