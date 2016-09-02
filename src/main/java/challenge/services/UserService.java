package challenge.services;

import challenge.model.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers(User requestingUser);
    public User getUser(User requestingUser, Long userId);

}
