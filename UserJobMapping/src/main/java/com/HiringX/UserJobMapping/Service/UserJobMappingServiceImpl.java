package com.HiringX.UserJobMapping.Service;

import com.HiringX.UserJobMapping.Entity.UserJobMapping;
import com.HiringX.UserJobMapping.Exception.JobMappingNotFoundException;
import com.HiringX.UserJobMapping.Repository.UserJobMappingRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserJobMappingServiceImpl implements UserJobMappingService{

    @Autowired
    private UserJobMappingRepository userjobmappingrepository;

    @Override
    public UserJobMapping postMapping(UserJobMapping jobmapping) {
        return userjobmappingrepository.save(jobmapping);
    }

    @Override
    public List<UserJobMapping> getAllMappings() {
        return userjobmappingrepository.findAll();
    }

    @Override
    public UserJobMapping getMappingById(Long id) {
        return userjobmappingrepository.findById(id).orElseThrow(()-> new JobMappingNotFoundException(id));
    }

    @Override
    public List<UserJobMapping> getMappingByParticularUser(Long userid) throws JobMappingNotFoundException{
        List<UserJobMapping> JobsForAUser=userjobmappingrepository.findAllByUserId(userid);
        if(JobsForAUser.isEmpty()) throw new JobMappingNotFoundException(userid);
        return JobsForAUser;
    }

    @Override
    public List<Long> getMappingByJobId(Long jobId) throws JobMappingNotFoundException{
        List<Long> UserIdsForAJob= userjobmappingrepository.findByJobId(jobId);
        if(UserIdsForAJob.isEmpty()) throw new JobMappingNotFoundException(jobId);
        return UserIdsForAJob;
    }

    @Override
    public List<Long> getJobIdsForAUser(Long userid) {
        List<UserJobMapping> listOfmapping=new ArrayList<>();
        List<Long> listofJobIds=new ArrayList<>();
        listOfmapping=userjobmappingrepository.findAllByUserId(userid);
        listOfmapping.forEach((ele)->{
            listofJobIds.add(ele.getUserJobId());
        });
        return listofJobIds;
    }

    @Override
    public void deleteJobMapping(Long jobId, Long userId) {
        userjobmappingrepository.deleteByUserJobIdANDUserId(jobId, userId);
    }
}
