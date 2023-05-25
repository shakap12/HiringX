package com.HiringX.UserService.Client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface JobClient {

    //to get job ids relating to a given company
    @GetExchange("/jobs/getjobids/{company}")
    public List<Long> getJobIdsForACompany(@PathVariable String company);

    //to get job ids relating to a given location
    @GetExchange("/jobs/getjobidsforlocation/{location}")
    public List<Long> getJobsIdsForLocation(@PathVariable String location);
}
