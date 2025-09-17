package ru.otus.qa.stub.springmvc.service;

import ru.otus.qa.stub.springmvc.model.UserModel;

import java.util.List;

public interface IUserService {

    UserModel getUserById(long id);

    List<UserModel> getAllUsers();

    UserModel addUser(UserModel userModel);

    boolean deleteUserByID(long id);

    UserModel updateUserById(long id, String firstName, String surName);
}
