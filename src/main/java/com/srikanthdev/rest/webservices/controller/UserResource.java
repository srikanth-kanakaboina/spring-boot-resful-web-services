package com.srikanthdev.rest.webservices.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


import com.srikanthdev.rest.webservices.exception.UserNotFoundException;
import com.srikanthdev.rest.webservices.model.User;
import com.srikanthdev.rest.webservices.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    //retrive all users
    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> retriveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/user/{id}")
    public Resource<User> getUser(@PathVariable int id) {
        User user=userDaoService.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id-"+id);

        Resource<User> resource=new Resource<>(user);
        ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retriveAllUsers());
        resource.add(linkTo.withRel("all-users"));


        return resource;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        User user=userDaoService.deleteById(id);
        if(user==null)
            throw new UserNotFoundException("id-"+id);
    }



    @PostMapping("/user")
    public ResponseEntity createUser( @Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);

        //Status of created

        URI location=ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(savedUser.getId())
                                    .toUri();
        return ResponseEntity.created(location).build();
    }

}
