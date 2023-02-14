package com.example.quoteservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(value = "user-api", url = "http://userservice:8080/api/user")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Optional getUserById(@PathVariable long id);
}

