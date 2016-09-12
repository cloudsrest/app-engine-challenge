package challenge.controller;

import challenge.BaseTest;
import challenge.dto.UserDTO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserControllerTest extends BaseTest {

    @Autowired
    UserController userController;

    @Test
    public void testGetUsers() {
        Principal mockPrinciple = getPrincipal();
        getUser("toUser");
        getUser("toUser1");
        getUser("toUser2");
        getUser("toUser3");
        getUser("toUser4");

        List<UserDTO> users = userController.getUsers(mockPrinciple);

        assertTrue(users.size() > 5);
    }

    @Test
    public void testGetUser() {
        Principal mockPrinciple = getPrincipal();

        UserDTO user = userController.getUser(testUser.getId(), mockPrinciple);

        assertEquals(testUser.getId(), user.getId());
        assertEquals(testUser.getUsername(), user.getUsername());
    }

    @Test
    public void testGetMe() {
        Principal mockPrinciple = getPrincipal();

        UserDTO me = userController.getMe(mockPrinciple);

        assertEquals(testUser.getId(), me.getId());
        assertEquals(testUser.getUsername(), me.getUsername());
    }

    private Principal getPrincipal() {
        Principal mockPrinciple = mock(Principal.class);
        when(mockPrinciple.getName()).thenReturn(testUser.getUsername());
        return mockPrinciple;
    }

}
