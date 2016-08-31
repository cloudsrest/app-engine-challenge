package challenge.controllers;

import challenge.Application;
import challenge.dto.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void testGetUsers() {
        assertNotNull(userController);
        List<User> users = userController.getUsers();
        assertNotNull(users);
        assertTrue(users.size() > 0);

    }

}
