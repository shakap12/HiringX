package com.HiringX.UserService.Config;

import com.HiringX.UserService.Client.JobClient;
import com.HiringX.UserService.Client.UserJobMappingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    //Web Client for communicating with JOB-MAPPING-SERVICE
    @Bean
    public WebClient UserJobMappingWebClient(){
        return WebClient.builder()
                .baseUrl("http://USER-JOB-MAPPING-SERVICE")
                .filter(filterFunction)
                .build();
    }
    @Bean
    public UserJobMappingClient userJobMappingClient(){
        HttpServiceProxyFactory httpserviceproxyfactory=HttpServiceProxyFactory.
                builder(WebClientAdapter.forClient(UserJobMappingWebClient()))
                .build();
        return httpserviceproxyfactory.createClient(UserJobMappingClient.class);
    }

    //Web Client for communicating with JOB-SERVICE
    @Bean
    public WebClient JobWebClient(){
        return WebClient.builder()
                .baseUrl("http://JOB-SERVICE")
                .filter(filterFunction)
                .build();
    }
    @Bean
    public JobClient jobClient(){
        HttpServiceProxyFactory httpServiceProxyFactory=HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(JobWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(JobClient.class);
    }

}
