package com.example.library.repository;

import com.example.library.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser,Integer> {

    @Query("SELECT u FROM ApplicationUser u WHERE u.email = :email")
    Optional<ApplicationUser> findUserByMail(String email);

}
