package challenge.controller;

import challenge.Application;
import challenge.dto.UserDTO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class UserControllerIntegrationTest extends BaseRestTest {

    @Test
    public void testGetUser() {
        UserDTO[] forNow = restTemplate.getForObject("http://localhost:9000/users", UserDTO[].class);
        List<UserDTO> users = Arrays.asList(forNow);

        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

}
