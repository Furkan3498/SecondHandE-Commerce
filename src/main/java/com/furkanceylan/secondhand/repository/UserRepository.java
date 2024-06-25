package com.furkanceylan.secondhand.repository;

import com.furkanceylan.secondhand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMail(String mail);
}
