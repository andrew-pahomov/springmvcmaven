package ru.otus.qa.stub.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.qa.stub.springmvc.service.IUserService;

@Controller
public class UserUiController {

    private final IUserService userService;

    public UserUiController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/ui")
    public String displayMessage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "usersView";
    }
}
