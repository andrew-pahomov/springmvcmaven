package ru.otus.qa.stub.springmvc.repository;

import org.springframework.stereotype.Repository;
import ru.otus.qa.stub.springmvc.model.UserModel;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final ArrayList<UserModel> users = new ArrayList<>();

    public UserModel findUserById(long id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public List<UserModel> getAllUsers() {
        return users;
    }

    public UserModel addUser(UserModel userModel) {
        users.add(userModel);
        return users.get(users.indexOf(userModel));
    }

    public boolean deleteUser(UserModel userToRemove) {
        return users.remove(userToRemove);
    }

    public void updateUserById(long id, UserModel userToUpdate) {
        users.set((int) id, userToUpdate);
    }
}
