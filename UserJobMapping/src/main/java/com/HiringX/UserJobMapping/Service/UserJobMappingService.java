package com.HiringX.UserJobMapping.Service;

import com.HiringX.UserJobMapping.Entity.UserJobMapping;

import java.util.HashMap;
import java.util.List;

public interface UserJobMappingService {

    //post a job mapped to a user
    public UserJobMapping postMapping(UserJobMapping jobmapping);

    //get details of all applied jobs
    public List<UserJobMapping> getAllMappings();

    //get details of specific job mapping
    public UserJobMapping getMappingById(Long id);

    //get details of jobs applied by a specific user
    public List<UserJobMapping> getMappingByParticularUser(Long userid);

    //get list of users who have applied for a specific job
    public List<Long> getMappingByJobId(Long jobId);

    //get job ids in which a particular user has applied
    public List<Long> getJobIdsForAUser(Long userid);

    //dekete job Mapping
    public void deleteJobMapping(Long jobId, Long userId);
}
