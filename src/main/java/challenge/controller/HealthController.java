package challenge.controller;

import challenge.dao.UserDao;
import challenge.dto.Health;
import challenge.model.User;
import challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/public/health")
public class HealthController {

    public static final String STATUS = "Up and Running!";

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Health health() {
        try {
            userDao.findAll();
        } catch (Exception e) {
            return new Health("Not feeling so good today, i had a bad dream... " + e.getMessage());
        }
        return new Health(STATUS);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
