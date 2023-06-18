package com.HiringX.UserService.Controller;

import com.HiringX.UserService.Client.UserJobMappingClient;
import com.HiringX.UserService.Entity.User;
import com.HiringX.UserService.Exception.UserNotFoundException;
import com.HiringX.UserService.Service.UserService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userserivce;

    @Autowired
    private UserJobMappingClient userJobMappingClient;

    private static final Logger log= LoggerFactory.getLogger(UserController.class);
    @PostMapping("/post")
    public User createUser(@Valid @RequestBody User user){
        log.info("Inside user controller-POST");
        return userserivce.postUser(user);
    }

    @GetMapping("/get")
    public List<User> getAllUsers(){
        log.info("Inside user controller-GET");
        return userserivce.getAllUsers();
    }

    @GetMapping("/get/{userid}")
    public User getUserById(@PathVariable Long userid){
        log.info("Inside user controller-GET/id");
        return userserivce.getUserById(userid);
    }

    @GetMapping("/getbyemail/{email}")
    public User getUserByEmail(@PathVariable String email){
        log.info("Inside user controller-GET/Email");
        User newUser=userserivce.getUserByEmail(email);
        if(newUser==null)throw new UserNotFoundException(email);
        return newUser;
    }

    @PutMapping("/put")
    public User updateUser(@RequestBody User user) {
        log.info("Inside user controller-PUT");
        return userserivce.updateUser(user.getUserId(),user);
    }
    @GetMapping("/getapplicationsforjob/{jobId}")
    public List<User> getUsersForAJob(@PathVariable Long jobId){
        log.info("Inside user controller-Get job applications for Job Id");
        return userserivce.getUsersForAJob(jobId);
    }

    @GetMapping("/getapplicationsforcompany/{company}")
    public HashMap<Long,List<User>> getUsersForACompany(@PathVariable String company){
        log.info("Inside user controller-Get job applications for given company");
        return userserivce.getUsersForACompany(company);
    }

    @GetMapping("/getapplicationsforlocation/{location}")
        public HashMap<Long,List<User>> getUsersForALocation(@PathVariable String location){
        log.info("Inside user controller-Get job applications on basis of location");
        return userserivce.getUsersForALocation(location);
    }
}
