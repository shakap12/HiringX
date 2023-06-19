package com.HiringX.UserJobMapping.Controller;

import com.HiringX.UserJobMapping.Entity.UserJobMapping;
import com.HiringX.UserJobMapping.Service.UserJobMappingService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userjobmapping")
@CrossOrigin("http://localhost:3000")
public class UserJobMappingController {

    @Autowired
    private UserJobMappingService userjobmappingservice;

    @PostMapping("/post")
    public UserJobMapping postUserJobMapping(@RequestBody UserJobMapping userjobmapping){
        return userjobmappingservice.postMapping(userjobmapping);
    }

    @GetMapping("/get")
    public List<UserJobMapping> getAllMappings(){
        return userjobmappingservice.getAllMappings();
    }

    @GetMapping("/get/{mappingid}")
    public UserJobMapping getMappingById(@PathVariable Long mappingid){
        return userjobmappingservice.getMappingById(mappingid);
    }

    // get jobs in which a specific user has applied
    @GetMapping("/getforuser/{userid}")
    public List<UserJobMapping> getMappingByUserId(@PathVariable Long userid){
        return userjobmappingservice.getMappingByParticularUser(userid);
    }

    //get users who have applied for a particular job
    @GetMapping("/getUsersForJob/{jobId}")
    public List<Long> getMappingByJobId(@PathVariable Long jobId){
        return userjobmappingservice.getMappingByJobId(jobId);
    }
    //get job ids in which a particular user has applied
    @GetMapping("/getjobidsforauser/{userid}")
    public List<Long> getJobIdsForAUser(@PathVariable Long userid){
        return userjobmappingservice.getJobIdsForAUser(userid);
    }

    @DeleteMapping("/deletemapping/{jobId}/{userId}")
    public void deleteJobMapping(@PathVariable Long jobId,@PathVariable Long userId){
         userjobmappingservice.deleteJobMapping(jobId,userId);
    }

}
