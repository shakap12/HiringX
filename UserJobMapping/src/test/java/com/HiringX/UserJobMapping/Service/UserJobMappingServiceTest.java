package com.HiringX.UserJobMapping.Service;

import com.HiringX.UserJobMapping.Entity.UserJobMapping;
import com.HiringX.UserJobMapping.Repository.UserJobMappingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserJobMappingServiceTest {

    @Autowired
    public UserJobMappingService userJobMappingService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void testForGetMappingByParticularUser() {
        long userId=2;
        UserJobMapping mappingObj1=UserJobMapping.builder().mappingId(1l).userId(2l).userJobId(57l).build();
        UserJobMapping mappingObj2=UserJobMapping.builder().mappingId(2l).userId(2l).userJobId(58l).build();
        UserJobMapping mappingObj3=UserJobMapping.builder().mappingId(3l).userId(2l).userJobId(59l).build();
        List<UserJobMapping> testList=new ArrayList<>();
            testList.add(mappingObj1);
            testList.add(mappingObj2);
            testList.add(mappingObj3);
        List<UserJobMapping> list=userJobMappingService.getMappingByParticularUser(userId);
        assertEquals(testList,list);
//        assertNotEquals(testList,list);
//        System.out.println(list);
    }

    @Test
    void testForGetMappingByJobId() {
            long jobId=59;
            List<Long> testList=new ArrayList<>();
            testList.add(2l);
            testList.add(53l);
            testList.add(54l);
            List<Long> list=userJobMappingService.getMappingByJobId(jobId);
            assertEquals(testList,list);
            //assertNotEquals(testList,list);
    }
}