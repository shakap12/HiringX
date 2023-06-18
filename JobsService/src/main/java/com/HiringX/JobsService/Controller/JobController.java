package com.HiringX.JobsService.Controller;

import com.HiringX.JobsService.Entity.Job;
import com.HiringX.JobsService.Repository.JobRepository;
import com.HiringX.JobsService.Service.JobService;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@CrossOrigin("http://localhost:3000")
public class JobController {

    @Autowired
    private JobService jobservice;

    private static final Logger log = LoggerFactory.getLogger(JobController.class);

    @PostMapping("/post")
    public Job postJob(@RequestBody Job job) {
        log.info("Inside Job Controller-POST");
        return jobservice.createJob(job);
    }

    @GetMapping("/get")
    public List<Job> getAllJobs() {
        log.info("Inside Job Controller-GET");
        return jobservice.getAllJobs();
    }

    @GetMapping("/get/{jobId}")
    public Job getJobById(@PathVariable Long jobId) {
        log.info("Inside Job Controller-GET/id");
        return jobservice.getJobById(jobId);
    }

    @PutMapping("/put")
    public Job updateJob(@RequestBody Job job) {
        log.info("Inside Job Controller-PUT");
        return jobservice.updateJob(job.getJobId(), job);
    }

    @GetMapping("/getbylocation/{location}")
    public List<Job> getJobsByLocation(@PathVariable String location) {
        log.info("Inside Job Controller-Get Jobs for given Location");
        return jobservice.getJobsOnBasisOfLocation(location);
    }

    @GetMapping("/getbycompany/{company}")
    public List<Job> getJobsByCompany(@PathVariable String company) {
        log.info("Inside Job Controller-Get Jobs for a given company");
        return jobservice.getJobsOnBasisOfCompany(company);
    }

    @GetMapping("/getbyskill/{skill}")
    public List<Job> getJobsBySkill(@PathVariable String skill) {
        log.info("Inside Job Controller-get Jobs for a given skill");
        return jobservice.getJobsOnBasisOfSkill(skill);
    }

    //get job ids related to a particular company
    @GetMapping("/getjobids/{company}")
    public List<Long> getJobIdsForACompany(@PathVariable String company) {
        log.info("Inside Job Controller-Get Job Id's for a given company");
        return jobservice.getJobIdsForACompany(company);
    }

    //get job ids related to a particular job location
    @GetMapping("/getjobidsforlocation/{location}")
    public List<Long> getJobsIdsForLocation(@PathVariable String location) {
        log.info("Inside Job Controller-Get Job Id's for a given location");
        return jobservice.getJobIdsForLocation(location);
    }

    @GetMapping("/getjobcompanies")
    public List<String> getCompanies() {
        log.info("Inside Job Controller-Get Companies");
        return jobservice.getAllCompanies();
    }

    @GetMapping("getjoblocations")
    public List<String> getLocations() {
        log.info("Inside Job Controller-Get Locations");
        return jobservice.getAllLocations();
    }

    @DeleteMapping("/delete/{jobId}")
    public void deleteJob(@PathVariable Long jobId) {
        log.info("Inside Job Controller-Delete Job");
        jobservice.deleteJobWithId(jobId);
    }

    @GetMapping("/getjobsforauser/{userid}")
    public List<Job> getJobsForAUser(@PathVariable Long userid) {
        log.info("Inside Job Controller-Get Jobs for Specific user Job");
        return jobservice.getJobsForAUser(userid);
    }
}

