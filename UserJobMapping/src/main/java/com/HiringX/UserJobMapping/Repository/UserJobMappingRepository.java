package com.HiringX.UserJobMapping.Repository;

import com.HiringX.UserJobMapping.Entity.UserJobMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserJobMappingRepository extends JpaRepository<UserJobMapping,Long> {

    //find all jobs for which a particular user has applied
    public List<UserJobMapping> findAllByUserId(Long userid);

    //find all users who have applied for a particular job
    public List<UserJobMapping>findAllByUserJobId(Long userjobid);

    //find all jobs related to a particular company. Should return all the user ids related to a particular job id
    //if job id is 3, should return all user ids associated with this job id
    @Query(value="SELECT `user id` FROM `user job mapping` WHERE `job id`=:jobId",nativeQuery = true)
    public List<Long> findByJobId(@Param("jobId") Long jobId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `user job mapping` WHERE `job id`=:jobId AND `user id`=:userId",nativeQuery = true)
    public void deleteByUserJobIdANDUserId(@Param("jobId") Long jobId,@Param("userId") Long userId);
}
