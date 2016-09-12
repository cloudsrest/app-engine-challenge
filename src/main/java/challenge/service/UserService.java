package challenge.service;

import challenge.model.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers(User requestingUser);
    public User getUser(User requestingUser, Long userId);
    public long geTotalUsers();
    public User createUser(User requestingUser, User user);

}
