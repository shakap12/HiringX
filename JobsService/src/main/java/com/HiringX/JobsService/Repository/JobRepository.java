package com.HiringX.JobsService.Repository;

import com.HiringX.JobsService.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {

    //search for jobs on basis of location
    public List<Job> findAllByJobLocation(String location);

    //search for jobs on basis of company
    public List<Job> findAllByJobCompany(String company);

    //search for jobs on basis of skill1
    public List<Job> findAllByJobRequiredSkill1OrJobRequiredSkill2(String skill1,String skill2);

    //search for jobs on basis of skill2
//    public List<Job> findAllByJobRequiredSkill2(String skill);

//    @Query(value = "SELECT jobid FROM job_table where job_company =:job_company",nativeQuery = true )
//    public List<Long> getJobIds(@Param(value = "job_company") String job_company);

    //search for job ids related to a particular company
//    @Query(value = "SELECT ")
//    public List<Long> findbyJobCompany()

    //to return the list containing all job ids belonging to a particular company
    @Query(value = "SELECT job_id FROM job_table WHERE job_company=:company",nativeQuery = true)
    public List<Long> findByJobCompany(@Param("company")String company);

    //to return a list containing all job ids belonging to a particular location
    @Query(value = "SELECT job_id FROM job_table WHERE job_location=:location",nativeQuery = true)
    public List<Long> findByJobLocation(@Param("location")String location);

}
