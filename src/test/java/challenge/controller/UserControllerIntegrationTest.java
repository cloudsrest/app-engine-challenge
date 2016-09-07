package challenge.controller;

import challenge.model.User;
import challenge.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Ignore
public class UserControllerIntegrationTest {
//    @Autowired
//    private MockMvc mvc;

//    @Autowired
//    private TestRestTemplate restTemplate;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        User user1 = new User();
        User user2 = new User();
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

        given(this.userService.getUsers(Matchers.<User>anyObject())).willReturn(list);
    }

    @Test
    public void testGetUsers() {
//        restTemplate.getForEntity("/", List.class);
    }

//    @Test
//    public void testGetUser() {
//        User bruce = getUserSaved("bruce");
//        System.out.println("bruce = " + bruce);
//
//        UserDTO fetched = restTemplate.getForObject(USER_BASE_URL + "/" + bruce.getId(), UserDTO.class);
//
//        assertNotNull(fetched);
//        assertEquals(new Long(bruce.getId()), fetched.getId());
//    }
//
//    @Test
//    public void testWhoAmI() {
//        User testUser1 = getTestUser();
//        userController.setRequester(testUser1);
//
//        UserDTO fetched = restTemplate.getForObject(USER_BASE_URL + "/me", UserDTO.class);
//
//        assertNotNull(fetched);
//        assertEquals(new Long(testUser1.getId()), fetched.getId());
//    }

}
