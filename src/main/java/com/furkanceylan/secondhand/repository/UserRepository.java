package com.furkanceylan.secondhand.repository;

import com.furkanceylan.secondhand.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByMail(String mail);
}
