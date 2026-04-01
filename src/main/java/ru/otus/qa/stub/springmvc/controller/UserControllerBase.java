package ru.otus.qa.stub.springmvc.controller;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.qa.stub.springmvc.exception.NotFoundException;
import ru.otus.qa.stub.springmvc.service.IUserService;

public abstract class UserControllerBase {

    protected final IUserService userService;

    public UserControllerBase(IUserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage handleException(NotFoundException exception) {
        return new ErrorMessage(exception.getMessage());
    }

}
