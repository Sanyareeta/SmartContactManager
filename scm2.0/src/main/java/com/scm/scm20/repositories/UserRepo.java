package com.scm.scm20.repositories;

import com.scm.scm20.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    boolean findByEmail(String email);
}
