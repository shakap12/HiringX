package com.HiringX.UserService.Client;

import com.HiringX.UserService.Entity.UserJobMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface UserJobMappingClient {

    @GetExchange("/userjobmapping/getforuser/{userid}")
    public List<UserJobMapping> getMappingByUserId(@PathVariable Long userid);

    @GetExchange("/userjobmapping/getUsersForJob/{jobId}")
    public List<Long> getMappingByJobId(@PathVariable Long jobId);

}
