package challenge.controller;

import challenge.BaseTest;
import challenge.dto.RecognitionTypeEnum;
import challenge.dto.UserDTO;
import challenge.model.Recognition;
import challenge.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void testGetUsers_withRec() {
        Principal mockPrinciple = getPrincipal();
        User toUser = getUser("toUser");
        User user1 = getUser("user1");
        User user2 = getUser("user2");
        User user3 = getUser("user3");
        User user4 = getUser("user4");

        Recognition recognition = new Recognition(testUser, toUser, RecognitionTypeEnum.DELIVERY, "good job", new Date());
        recognitionDao.save(recognition);

        List<UserDTO> users = userController.getUsers(mockPrinciple);
        for (UserDTO user : users) {
            if (user.getUsername().equals(toUser.getUsername())) {
                assertFalse(user.isCanGiveTo());
            }
        }
        System.out.println("users = " + users);
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
