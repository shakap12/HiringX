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
        String location="chennai";
        String failLocation="chn";
        List<Job> testList=new ArrayList<>();
        Job job= Job.builder()
                .jobId(104l)
                .jobTitle("SDE")
                .jobRequiredSkill1("Java")
                .jobRequiredSkill2("DevOps")
                .jobCompany("Maersk")
                .jobLocation("Chennai")
                .build();
        testList.add(job);
        List<Job> passList=jobService.getJobsOnBasisOfLocation(location);
//      List<Job> failList=jobService.getJobsOnBasisOfLocation(failLocation);
        assertEquals(passList,testList);
//        assertNotEquals(failList,testList);

    }

    @Test
    void getJobsOnBasisOfSkill() {
        String skill="DevOps";
        String failSkill="rect";
        List<Job> testList=new ArrayList<>();
        Job job= Job.builder()
                .jobId(104l)
                .jobTitle("SDE")
                .jobRequiredSkill1("Java")
                .jobRequiredSkill2("DevOps")
                .jobCompany("Maersk")
                .jobLocation("Chennai")
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
                .jobId(104l)
                .jobTitle("SDE")
                .jobRequiredSkill1("Java")
                .jobRequiredSkill2("DevOps")
                .jobCompany("Maersk")
                .jobLocation("Chennai")
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
        list.add(104l);
        List<Long> testList=jobService.getJobIdsForACompany(company);

        assertEquals(testList,list);
    }

    @Test
    void getJobIdsForLocation() {
        String location="Chennai";
        List<Long> list=new ArrayList<>();
        list.add(104l);
        List<Long> testList=jobService.getJobIdsForLocation(location);
        assertEquals(testList,list);
    }
}