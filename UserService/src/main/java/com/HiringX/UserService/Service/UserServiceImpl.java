package com.HiringX.UserService.Service;

import com.HiringX.UserService.Client.JobClient;
import com.HiringX.UserService.Client.UserJobMappingClient;
import com.HiringX.UserService.Entity.User;
import com.HiringX.UserService.Entity.UserJobMapping;
import com.HiringX.UserService.Exception.CompanyNotFoundException;
import com.HiringX.UserService.Exception.LocationNotFoundException;
import com.HiringX.UserService.Exception.UserNotFoundException;
import com.HiringX.UserService.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.HttpHeaders;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userrepository;

    public WebClient userJobMappingWebclient;
    public WebClient jobWebClient;

    private static final Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

    @PostConstruct
    public void init() {
        log.info("Web Clients getting initialized");
        userJobMappingWebclient = WebClient.builder()
                .baseUrl("http://localhost:8084/userjobmapping/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        jobWebClient = WebClient.builder().baseUrl("http://localhost:8084/jobs/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public User postUser(User user) {
        log.info("User posted successfully");
        return userrepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = userrepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        log.info("Fetching user from User Id");
        if (Objects.nonNull(user)) user.setAppliedJobs(
                userJobMappingWebclient.get()
                        .uri("/getforuser" + user.getUserId())
                        .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new UserNotFoundException(user.getUserId()))).bodyToFlux(UserJobMapping.class).collectList().block());
        else log.warn("No User found with given Id");
        return user;
    }



    @Override
    public List<User> getAllUsers() {
        List<User> allUser=userrepository.findAll();
        log.info("Fetching all users from repository");
        allUser.forEach(user -> userJobMappingWebclient
                        .get()
                        .uri("getforuser/"+user.getUserId())
                        .retrieve()
                        .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> Mono.error(new UserNotFoundException(user.getUserId())))
                        .bodyToFlux(UserJobMapping.class)
                        .collectList()
                        .block()
                );
        return allUser;
    }

    @Override
    public User updateUser(Long userId, User user){
        log.info("User updated with new values");
        return userrepository.findById(userId).map(newuser->{
            newuser.setUserId(user.getUserId());
            newuser.setUserName(user.getUserName());
            newuser.setUserEmail(user.getUserEmail());
            newuser.setSkill1(user.getSkill1());
            newuser.setSkill2(user.getSkill2());
            return userrepository.save(newuser);
        }).orElseThrow(()-> new UserNotFoundException(userId));
    }

    @Override
    public List<User> getUsersForAJob(Long jobId) {
        log.info("Inside get USERS for the given JOB ID");
        List<User> usersForAJobWithDetails=new ArrayList<>();
        List<Long> usersForAJob=userJobMappingWebclient
                .get()
                .uri("/getUsersForJob/"+jobId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> Mono.error(new UserNotFoundException(jobId)))
                .bodyToFlux(Long.class)
                .collectList()
                .block();
        usersForAJob.forEach(ele->{
            User user=userrepository.findById(ele).orElseThrow(()-> new UserNotFoundException(ele));
            usersForAJobWithDetails.add(user);
        });
        return usersForAJobWithDetails;
    }

    @Override
    public HashMap<Long, List<User>> getUsersForACompany(String company) {
        log.info("Getting user details for a given company");
        //to store the job ids related to the company passed in parameter
        List<Long> jobsIdsForGivenCompany=jobWebClient
                .get()
                .uri("getjobids/"+company)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> Mono.error(new CompanyNotFoundException(company)))
                .bodyToFlux(Long.class)
                .collectList()
                .block();
        HashMap<Long,List<User>> mapStoringUsersForEachJob=new HashMap<>();
        jobsIdsForGivenCompany.forEach(ele->{

            List<Long> usersForAParticularJob=new ArrayList<>();
            try{
                log.info("User Id's for particular company fetched successfully");
                usersForAParticularJob=userJobMappingWebclient.get()
                        .uri("getUsersForJob/"+ele)
                        .retrieve()
                        .bodyToFlux(Long.class)
                        .collectList()
                        .block();
            }
            catch (Exception e){
                log.warn("No job mapping found for the given job id-"+ele);
                System.out.println("No users found for job id-"+ele);
            }
            finally {
                log.info("User details for job id-"+ele+" stored in map successfully");
                List<User> userDetailsForParticularJobId=new ArrayList<>();
                usersForAParticularJob.forEach(val->{
                    User newUser=userrepository.findById(val).orElse(null);
                    userDetailsForParticularJobId.add(newUser);
                });
                mapStoringUsersForEachJob.put(ele, userDetailsForParticularJobId);
            }
        });
        return mapStoringUsersForEachJob;

    }

    @Override
    public HashMap<Long, List<User>> getUsersForALocation(String location) {
        log.info("Getting user details for a given location");
        List<Long> jobIdsForGivenLocation=jobWebClient
                .get()
                .uri("getjobidsforlocation/"+location)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> Mono.error(new LocationNotFoundException(location)))
                .bodyToFlux(Long.class)
                .collectList()
                .block();
        HashMap<Long,List<User>> mapStoringUserDetailsForEachLocation=new HashMap<>();
        jobIdsForGivenLocation.forEach(ele->{
            List<Long> userForParticularLocation=new ArrayList<>();
            try{
                log.info("Job Id's for a given location fetched successfully");
                userForParticularLocation=userJobMappingWebclient
                        .get()
                        .uri("getUsersForJob/"+ele)
                        .retrieve()
                        .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> Mono.error(new UserNotFoundException(27l)))
                        .bodyToFlux(Long.class)
                        .collectList()
                        .block();
            }
            catch (Exception e){
                log.warn("No user mapping found for given job Id"+ele);
                System.out.println("No users found for location-"+ele);
            }
            finally {
                log.info("Details of users for a particular location stored in hashmap successfully");
                List<User> userDetailsForParticularJobLocation=new ArrayList<>();
                userForParticularLocation.forEach(val->{
                    User newUser=userrepository.findById(val).orElse(null);
                    System.out.println("enrolled USER--"+val);
                    userDetailsForParticularJobLocation.add(newUser);
                });
                mapStoringUserDetailsForEachLocation.put(ele,userDetailsForParticularJobLocation);
            }
        });
        return mapStoringUserDetailsForEachLocation;
   }
}