package ru.otus.qa.stub.springmvc.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.qa.stub.springmvc.model.UserModel;
import ru.otus.qa.stub.springmvc.repository.UserRepository;

import java.util.List;
import java.util.stream.LongStream;

@Service
@ConditionalOnProperty(prefix = "user.service", name = "version", havingValue = "repository", matchIfMissing = false)
public class UserServiceRepositoryImpl implements IUserService {
    private final UserRepository userRepository;

    public UserServiceRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        LongStream.range(0, 10)
                .forEach(i -> userRepository.addUser(new UserModel(i, "Имя " + i, "Фамилия " + i)));
    }

    @Override
    public UserModel getUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserModel addUser(UserModel userModel) {
        getAllUsers().stream().map(UserModel::getId).max(Long::compareTo).ifPresent(id -> {
            userModel.setId(id + 1);
            userRepository.addUser(userModel);
        });
        return getUserById(userModel.getId());
    }

    @Override
    public boolean deleteUserByID(long id) {
        UserModel userToRemove = userRepository.findUserById(id);
        return userRepository.deleteUser(userToRemove);
    }

    @Override
    public UserModel updateUserById(long id, String firstName, String surName) {
        UserModel userToUpdate = userRepository.findUserById(id);
        userToUpdate.setFirstName(firstName);
        userToUpdate.setSurName(surName);
        userRepository.updateUserById(id, userToUpdate);
        return userRepository.findUserById(id);
    }
}
