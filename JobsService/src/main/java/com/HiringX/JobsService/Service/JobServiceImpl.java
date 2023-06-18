package com.HiringX.JobsService.Service;

import com.HiringX.JobsService.Entity.Job;
import com.HiringX.JobsService.Exception.JobNotFoundException;
import com.HiringX.JobsService.Repository.JobRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobrepository;

    public WebClient jobMappingWebClient;
    public static final Logger log= LoggerFactory.getLogger(JobServiceImpl.class);


    @PostConstruct
    public void init() {
        log.info("Web Clients getting initialized");
        jobMappingWebClient= WebClient.builder()
                .baseUrl("http://localhost:8084/userjobmapping/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    @Override
    public Job createJob(Job job) {
        log.info("Job posted successfully");
        return jobrepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        log.info("Fetching job for a job id");
        return jobrepository.findById(id).orElseThrow(()-> new JobNotFoundException(id));
    }

    @Override
    public List<Job> getAllJobs() {
        log.info("Fetching all jobs");
        return jobrepository.findAll();
    }

    @Override
    public Job updateJob(Long id, Job job) {
        log.info("Job updated with new values");
        return jobrepository.findById(id).map( newjob->{
           if(Objects.nonNull(job.getJobCompany()) && job.getJobCompany()!="")newjob.setJobCompany(job.getJobCompany());
           if(Objects.nonNull(job.getJobLocation()) && job.getJobLocation()!="")newjob.setJobLocation(job.getJobLocation());
           if(Objects.nonNull(job.getJobTitle()) && job.getJobTitle()!="")newjob.setJobTitle(job.getJobTitle());
           if(Objects.nonNull(job.getJobRequiredSkill1()) && job.getJobRequiredSkill1()!="")newjob.setJobRequiredSkill1(job.getJobRequiredSkill1());
           if(Objects.nonNull(job.getJobRequiredSkill2()) && job.getJobRequiredSkill2()!="")newjob.setJobRequiredSkill2(job.getJobRequiredSkill2());
           return jobrepository.save(newjob);
        }).orElseThrow(()-> new JobNotFoundException(id));
    }

    @Override
    public List<Job> getJobsOnBasisOfLocation(String location) throws JobNotFoundException{
        log.info("Inside job service, Getting Jobs for a given location");
        List<Job> jobForALocation=jobrepository.findAllByJobLocation(location);
        if(jobForALocation.isEmpty()){
            log.warn("No Jobs found for given Location");
            throw new JobNotFoundException(location);
        }
        return jobForALocation;
    }

    @Override
    public List<Job> getJobsOnBasisOfSkill(String skill) throws JobNotFoundException {
        log.info("Inside job serivce, Getting Jobs on basis of given skill");
        List<Job> skilljobs=jobrepository.findAllByJobRequiredSkill1OrJobRequiredSkill2(skill,skill);
        if(skilljobs.isEmpty()){
            log.warn("No Jobs found with mentioned Skill");
            throw new JobNotFoundException(skill);
        }
        return skilljobs;
    }

    @Override
    public List<Job> getJobsOnBasisOfCompany(String company) throws JobNotFoundException {
        log.info("Inside job service, Getting Jobs on basis on given company");
        List<Job> JobForCompany=jobrepository.findAllByJobCompany(company);
        if(JobForCompany.isEmpty()){
            log.warn("No Jobs found for mentioned Company");
            throw new JobNotFoundException(company);
        }
        else return JobForCompany;
    }

    @Override
    public List<Long> getJobIdsForACompany(String company) throws JobNotFoundException{
        log.info("Inside job service, Getting Job Ids for a given Company");
        List<Long> jobIdsForCompany=jobrepository.findByJobCompany(company);
        if(jobIdsForCompany.isEmpty()){
            log.warn("No Job ID found for mentioned company");
            throw new JobNotFoundException("company",company);
        }
        else return jobIdsForCompany;
    }

    @Override
    public List<Long> getJobIdsForLocation(String location) throws JobNotFoundException{
        log.info("Inside job service, Getting Job Ids for a given location");
        List<Long> jobIdsForLocation= jobrepository.findByJobLocation(location);
        if(jobIdsForLocation.isEmpty()){
            log.warn("No Job Id found for mentioned location");
            throw new JobNotFoundException("location",location);
        }
        else return jobIdsForLocation;
    }

    @Override
    public List<String> getAllCompanies() {
        List<String> companies=new ArrayList<>();
        List<Job> newList=jobrepository.findAll();
        for(Job ele:newList){
            if(!companies.contains(ele.getJobCompany()))companies.add(ele.getJobCompany());
        }
        return companies;
    }

    @Override
    public List<String> getAllLocations() {
        List<String> locations=new ArrayList<>();
        List<Job> newList=jobrepository.findAll();
        for(Job ele:newList){
            if(!locations.contains(ele.getJobLocation()))locations.add(ele.getJobLocation());
        }
        return locations;
    }

    @Override
    public void deleteJobWithId(Long jobId) {
        log.warn("Deleting job with ID-"+jobId);
        jobrepository.deleteById(jobId);
    }

    @Override
    public List<Job> getJobsForAUser(Long userid) {
        List<Long> listofJobIdsForAUser = jobMappingWebClient.get()
                .uri("getjobidsforauser/" + userid)
                .retrieve()
                .bodyToFlux(Long.class).
                collectList().
                block();

        System.out.println(listofJobIdsForAUser);
        List<Job> jobsForASpecificUser = new ArrayList<>();
        for(Long ele:listofJobIdsForAUser) {
            try {
                jobsForASpecificUser.add(jobrepository.findById(ele).orElseThrow(() -> new JobNotFoundException(ele)));
            } catch (JobNotFoundException e) {
                System.out.println("No Job found for given Id");
            } finally {
                continue;
            }
        }
        return jobsForASpecificUser;
    }

}
