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
        long userId=1;
        UserJobMapping mappingObj1=UserJobMapping.builder().mappingId(1l).userId(1l).userJobId(1l).build();
        UserJobMapping mappingObj2=UserJobMapping.builder().mappingId(2l).userId(1l).userJobId(2l).build();
        UserJobMapping mappingObj3=UserJobMapping.builder().mappingId(3l).userId(1l).userJobId(3l).build();
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
            long jobId=3;
            List<Long> testList=new ArrayList<>();
            testList.add(1l);
            testList.add(2l);
            List<Long> list=userJobMappingService.getMappingByJobId(jobId);
            assertEquals(testList,list);
            //assertNotEquals(testList,list);
    }
}