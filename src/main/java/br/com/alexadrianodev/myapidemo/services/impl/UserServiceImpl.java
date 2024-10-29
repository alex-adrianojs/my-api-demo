package br.com.alexadrianodev.myapidemo.services.impl;


import br.com.alexadrianodev.myapidemo.domain.User;
import br.com.alexadrianodev.myapidemo.domain.dto.UserDTO;
import br.com.alexadrianodev.myapidemo.repositories.UserRepository;
import br.com.alexadrianodev.myapidemo.services.UserService;
import br.com.alexadrianodev.myapidemo.services.exceptions.DataIntegratyViolatedExcpetion;
import br.com.alexadrianodev.myapidemo.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    @Override
    public void findByEmail(UserDTO user){
        Optional<User> userEntity = repository.findByEmail(user.getEmail());
        if(userEntity.isPresent() && !userEntity.get().getId().equals(user.getId())){
            throw new DataIntegratyViolatedExcpetion("This email is already registered");
        }
    }

    @Override
    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    public User create(UserDTO user) {
        findByEmail(user);
        return repository.save(mapper.map(user, User.class));
    }

    @Override
    public User update(UserDTO user) {
        findByEmail(user);
        return repository.save(mapper.map(user, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

  }
