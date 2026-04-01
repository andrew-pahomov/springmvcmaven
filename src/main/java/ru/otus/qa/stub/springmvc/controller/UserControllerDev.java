package ru.otus.qa.stub.springmvc.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import ru.otus.qa.stub.springmvc.model.UserModel;
import ru.otus.qa.stub.springmvc.service.IUserService;

@RestController
@Profile({"dev"})
@RequestMapping(path = "/users")
public class UserControllerDev extends UserControllerBase {

    public UserControllerDev(IUserService userService) {
        super(userService);
    }

    @PostMapping()
    public UserModel addUser(@RequestBody UserModel userModel) {
        return userService.addUser(userModel);
    }

    @DeleteMapping(path = "/{id}")
    public void removeUserById(@PathVariable("id") long id) {
        userService.deleteUserByID(id);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUserByIs(@PathVariable("id") long id,
                                    @RequestParam("firstName") String firstName, @RequestParam("surName") String surName) {
        return userService.updateUserById(id, firstName, surName);
    }

}
