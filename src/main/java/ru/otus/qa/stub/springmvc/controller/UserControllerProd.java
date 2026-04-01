package ru.otus.qa.stub.springmvc.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import ru.otus.qa.stub.springmvc.model.UserModel;
import ru.otus.qa.stub.springmvc.service.IUserService;

import java.util.List;

@RestController
@Profile({"prod", "dev"})
@RequestMapping(path = "/users")
public class UserControllerProd extends UserControllerBase {

    public UserControllerProd(IUserService userService) {
        super(userService);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public UserModel getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

}
