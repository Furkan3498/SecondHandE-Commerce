package com.furkanceylan.secondhand.repository;

import com.furkanceylan.secondhand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
