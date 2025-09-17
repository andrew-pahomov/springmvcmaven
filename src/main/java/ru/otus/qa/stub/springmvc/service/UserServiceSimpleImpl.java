package ru.otus.qa.stub.springmvc.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.qa.stub.springmvc.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@Service
@ConditionalOnProperty(prefix = "user.service", name = "version", havingValue = "simple", matchIfMissing = true)
public class UserServiceSimpleImpl implements IUserService {
    private final ArrayList<UserModel> users = new ArrayList<>();

    public UserServiceSimpleImpl() {
        LongStream.range(0, 5).forEach(i -> users.add(new UserModel(i, "Имя " + i, "Фамилия " + i)));
    }

    public UserModel getUserById(long id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public List<UserModel> getAllUsers() {
        return users;
    }

    public UserModel addUser(UserModel userModel) {
        users.stream().map(UserModel::getId).max(Long::compareTo).ifPresent(id -> {
            userModel.setId(id + 1);
            users.add(userModel);
        });
        return users.get(users.indexOf(userModel));
    }

    public boolean deleteUserByID(long id) {
        return users.remove(getUserById(id));
    }

    public UserModel updateUserById(long id, String firstName, String surName) {
        UserModel userToUpdate = getUserById(id);
        userToUpdate.setFirstName(firstName);
        userToUpdate.setSurName(surName);
        return getUserById(id);
    }
}
