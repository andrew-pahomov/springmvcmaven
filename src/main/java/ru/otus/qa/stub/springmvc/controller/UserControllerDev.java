package ru.otus.qa.stub.springmvc.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.qa.stub.springmvc.model.UserModel;
import ru.otus.qa.stub.springmvc.service.IUserService;

@RestController
@Profile({"dev"})
@RequestMapping(path = "/users")
public class UserControllerDev {

    private final IUserService userService;

    public UserControllerDev(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserModel addUser(@RequestBody UserModel userModel) {
        return userService.addUser(userModel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> removeUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.deleteUserByID(id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUserByIs(@PathVariable("id") long id,
                                    @RequestParam("firstName") String firstName, @RequestParam("surName") String surName) {
        return userService.updateUserById(id, firstName, surName);
    }
}
