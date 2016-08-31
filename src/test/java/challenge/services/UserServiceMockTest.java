package challenge.services;

import challenge.dto.User;
import challenge.services.impl.UserServiceMock;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserServiceMockTest {



    @Test
    public void testGetUsers() {
        UserServiceMock userServiceMock = new UserServiceMock();
        User requestingUser = new User();
        List<User> users = userServiceMock.getUsers(requestingUser);
        Assert.assertNotNull(users);

        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

}
