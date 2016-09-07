package challenge.controller;

import challenge.dao.UserDao;
import challenge.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class BaseController {

    @Autowired
    UserDao userDao;

    protected User requestor(Principal user) {
        return userDao.findByUsername(user.getName());
    }

}
