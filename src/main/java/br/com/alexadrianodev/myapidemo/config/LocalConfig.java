package br.com.alexadrianodev.myapidemo.config;

import br.com.alexadrianodev.myapidemo.domain.User;
import br.com.alexadrianodev.myapidemo.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;
    @PostConstruct
    public void startDB(){
        User u1 = new User(null, "Alex Adriano", "alex.adriano@gmail.com", "KSH");
        User u2 = new User(null, "Pedro de Lara", "pedro.lara@gmail.com", "KLM");
        repository.saveAll(List.of(u1, u2));
    }
}
