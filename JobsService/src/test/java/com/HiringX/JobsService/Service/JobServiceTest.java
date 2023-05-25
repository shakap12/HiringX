package com.HiringX.JobsService.Service;

import com.HiringX.JobsService.Entity.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobServiceTest {

    @Autowired
    private JobService jobService;

    @Test
    void TestForGetJobsOnBasisOfLocation() {
        String location="gurgaon";
        String failLocation="gurg";
        List<Job> testList=new ArrayList<>();
        Job job= Job.builder()
                .jobId(3l)
                .jobTitle("FSE")
                .jobRequiredSkill1("ReactJS")
                .jobRequiredSkill2("Spring Boot")
                .jobCompany("Cognizant")
                .jobLocation("Gurgaon")
                .build();
        testList.add(job);
        List<Job> passList=jobService.getJobsOnBasisOfLocation(location);
//      List<Job> failList=jobService.getJobsOnBasisOfLocation(failLocation);
        assertEquals(passList,testList);
//        assertNotEquals(failList,testList);

    }

    @Test
    void getJobsOnBasisOfSkill() {
        String skill="ReactJS";
        String failSkill="rect";
        List<Job> testList=new ArrayList<>();
        Job job= Job.builder()
                .jobId(3l)
                .jobTitle("FSE")
                .jobRequiredSkill1("ReactJS")
                .jobRequiredSkill2("Spring Boot")
                .jobCompany("Cognizant")
                .jobLocation("Gurgaon")
                .build();
        testList.add(job);
        List<Job> passList=jobService.getJobsOnBasisOfSkill(skill);
//        List<Job> failList=jobService.getJobsOnBasisOfLocation(failSkill);
        assertEquals(passList,testList);
//        assertNotEquals(failList,testList);
    }

    @Test
    void getJobsOnBasisOfCompany() {
        String company="Maersk";
        String failCompany="mers";
        List<Job> testList=new ArrayList<>();
        Job job= Job.builder()
                .jobId(52l)
                .jobTitle("FSE")
                .jobRequiredSkill1("Node")
                .jobRequiredSkill2("Angular")
                .jobCompany("Maersk")
                .jobLocation("Noida")
                .build();
        testList.add(job);
        List<Job> passList=jobService.getJobsOnBasisOfCompany(company);
//        List<Job> failList=jobService.getJobsOnBasisOfLocation(failCompany);
        assertEquals(passList,testList);
//        assertNotEquals(failList,testList);
    }

    @Test
    void getJobIdsForACompany() {
        String company="maersk";
        List<Long> list=new ArrayList<>();
        list.add(52l);
        List<Long> testList=jobService.getJobIdsForACompany(company);

        assertEquals(testList,list);
    }

    @Test
    void getJobIdsForLocation() {
        String location="Noida";
        List<Long> list=new ArrayList<>();
        list.add(2l);
        list.add(52l);
        List<Long> testList=jobService.getJobIdsForLocation(location);
        assertEquals(testList,list);
    }
}