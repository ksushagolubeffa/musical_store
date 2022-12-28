package services;

import models.User;
import repositories.UserRepository;

import java.util.List;

public class UserService {
    private static final UserRepository repository = new UserRepository();

    public void saveUser(User user) {
        repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void updateUser(User user) {
        repository.update(user);
    }
}
