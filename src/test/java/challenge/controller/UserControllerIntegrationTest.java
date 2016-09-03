package challenge.controller;

import challenge.Application;
import challenge.dto.UserDTO;
import challenge.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class UserControllerIntegrationTest extends BaseRestTest {

    public static final String USER_BASE_URL = "http://localhost:9000/users";

    @Autowired
    private UserController userController;

    @Test
    public void testGetUsers() {
        UserDTO[] forNow = restTemplate.getForObject(USER_BASE_URL, UserDTO[].class);
        List<UserDTO> users = Arrays.asList(forNow);

        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testGetUser() {
        User bruce = getUserSaved("bruce");
        System.out.println("bruce = " + bruce);

        UserDTO fetched = restTemplate.getForObject(USER_BASE_URL + "/" + bruce.getId(), UserDTO.class);

        assertNotNull(fetched);
        assertEquals(new Long(bruce.getId()), fetched.getId());
    }

    @Test
    public void testWhoAmI() {
        User testUser1 = getTestUser();
        userController.setRequester(testUser1);

        UserDTO fetched = restTemplate.getForObject(USER_BASE_URL + "/me", UserDTO.class);

        assertNotNull(fetched);
        assertEquals(new Long(testUser1.getId()), fetched.getId());
    }

}
