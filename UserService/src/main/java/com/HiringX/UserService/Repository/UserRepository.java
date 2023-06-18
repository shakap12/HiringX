package com.HiringX.UserService.Repository;

import com.HiringX.UserService.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    //find user by the User Email
    public User findByUserEmail(String email);
}
