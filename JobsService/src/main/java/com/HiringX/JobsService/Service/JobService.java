package com.HiringX.JobsService.Service;

import com.HiringX.JobsService.Entity.Job;

import java.util.List;

public interface JobService {

    //create a new job
    public Job createJob(Job job);

    //get a job by jobID
    public Job getJobById(Long id);

    //get all jobs
    public List<Job> getAllJobs();

    //update a job
    public Job updateJob(Long id,Job job);

    //get all jobs on basis of location

    public List<Job> getJobsOnBasisOfLocation(String location);

    //get all jobs on basis of skill
    public List<Job> getJobsOnBasisOfSkill(String skill);

    //get all jobs on basis of company
    public List<Job> getJobsOnBasisOfCompany(String company);


    //get jobs ids belonging to a particular company
    public List<Long> getJobIdsForACompany(String company);

    //get jobs ids belonging to a particular location
    public List<Long> getJobIdsForLocation(String location);

    //get all companies
    public List<String> getAllCompanies();

    //get all job locations
    public List<String> getAllLocations();

    //Delete job with passed job ID
    public void deleteJobWithId(Long jobId);

    //get jobs for a specific user
    public List<Job> getJobsForAUser(Long userid);
}
