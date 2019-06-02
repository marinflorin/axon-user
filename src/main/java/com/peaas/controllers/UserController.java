package com.peaas.controllers;

import com.peaas.aggregates.CreateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private CommandGateway commandGateway;


    @PostMapping
    public UserRequest createUserProfile(@RequestBody UserRequest user) {
        commandGateway.send(new CreateUserCommand(UUID.randomUUID().toString(), user.getName(), user.getSurname(), user.getAddress(), user.getEmail()));

        return user;
    }
}
