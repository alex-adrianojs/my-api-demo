package br.com.alexadrianodev.myapidemo.repositories;

import br.com.alexadrianodev.myapidemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
