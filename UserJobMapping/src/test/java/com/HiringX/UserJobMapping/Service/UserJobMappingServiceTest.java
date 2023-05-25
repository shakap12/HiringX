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
        UserJobMapping mappingObj2=UserJobMapping.builder().mappingId(2l).userId(2l).userJobId(1l).build();
        UserJobMapping mappingObj3=UserJobMapping.builder().mappingId(3l).userId(3l).userJobId(1l).build();
        List<UserJobMapping> testList=new ArrayList<>();
            testList.add(mappingObj1);
        List<UserJobMapping> list=userJobMappingService.getMappingByParticularUser(userId);
        assertEquals(testList,list);
        System.out.println(list);
    }

    @Test
    void testForgetMappingByJobId() {

    }
}