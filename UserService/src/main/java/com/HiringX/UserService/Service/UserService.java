package com.HiringX.UserService.Service;

import com.HiringX.UserService.Entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

    //creating new user
    public User postUser(User user);

    //get single user as per userId
    public User getUserById(Long id);

    //get all users
    public List<User> getAllUsers();

    //update any user
    public User updateUser(Long userId,User user);

    //get applications for a particular job with all details
    public List<User> getUsersForAJob(Long jobId);

    // to return a map containg list of users belonging to different jobs of a same company
    public HashMap<Long, List<User>> getUsersForACompany(String company);

    // to return a map containing list of users belonging to different jobs of different/same company but SAME Location
    public HashMap<Long ,List<User>> getUsersForALocation(String location);

    //to return a single user as per the EMAIL
    public User getUserByEmail(String email);
}
