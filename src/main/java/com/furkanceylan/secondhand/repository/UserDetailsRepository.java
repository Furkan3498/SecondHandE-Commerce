package com.furkanceylan.secondhand.repository;

import com.furkanceylan.secondhand.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
