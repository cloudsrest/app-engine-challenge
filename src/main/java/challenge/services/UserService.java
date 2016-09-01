package challenge.services;

import challenge.dto.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers(User requestingUser);
    public User getUser(String userId);

}
