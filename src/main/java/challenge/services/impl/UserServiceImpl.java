package challenge.services.impl;

import challenge.daos.UserDao;
import challenge.model.User;
import challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers(User requestingUser) {
        return userDao.findAll();
    }

    @Override
    public User getUser(User requestingUser, Long userId) {
        return userDao.findOne(userId);
    }
}
