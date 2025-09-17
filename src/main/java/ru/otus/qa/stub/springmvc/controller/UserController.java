package ru.otus.qa.stub.springmvc.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.qa.stub.springmvc.model.UserModel;
import ru.otus.qa.stub.springmvc.service.IUserService;

import java.util.List;

@RestController
@Profile({"prod", "dev"})
@RequestMapping(path = "/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") long id) {
        UserModel userModel = userService.getUserById(id);
        return new ResponseEntity<>(userModel, userModel != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
