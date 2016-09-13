package challenge.integration;

import challenge.BaseTest;
import challenge.dto.RecognitionDTO;
import challenge.dto.TokenDTO;
import challenge.dto.UserDTO;
import challenge.model.Team;
import challenge.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest extends BaseIntegrationTest {

    String usersUrl = "/api/secure/users";
    String meUrl = "/api/secure/users/me";

    @Test
    public void testGetUsers_not_authenticated() throws IOException {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(usersUrl, String.class);

        assertUnauthorized(forEntity);
    }

    @Test
    public void testGetUser_not_authenticated() throws IOException {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(usersUrl, String.class);

        assertUnauthorized(forEntity);
    }

    @Test
    public void testGetMe_not_authenticated() throws IOException {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(meUrl, String.class);

        assertUnauthorized(forEntity);
    }

    @Test
    public void testGetUsers_authenticated() throws IOException {
        String usr1 = "usr1";
        getUser(usr1);
        getUser("usr2");
        getUser("usr3");
        TokenDTO accessToken = getAccessToken(testUser);

        ResponseEntity<UserDTO[]> exchange = restTemplate.exchange(usersUrl, HttpMethod.GET, buildTokenHeader(accessToken, null), UserDTO[].class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        List<UserDTO> userDTOs = Arrays.asList(exchange.getBody());
        assertNotNull(userDTOs);
        assertTrue(userDTOs.size() > 2);

        UserDTO userDto = userDTOs.stream().filter(user -> user.getUsername().equals(getTestUserName(usr1))).findFirst().get();
        assertNotNull(userDto);
        assertEquals(getTestUserName(usr1), userDto.getUsername());
    }

    @Test
    public void testGetUser_authenticated() throws IOException {
        TokenDTO accessToken = getAccessToken(testUser);

        ResponseEntity<UserDTO> exchange = restTemplate.exchange(usersUrl + "/" + testUser.getId(), HttpMethod.GET, buildTokenHeader(accessToken, null), UserDTO.class);

        assertNotNull(exchange);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        UserDTO userDTO = exchange.getBody();
        assertNotNull(userDTO);
        assertEquals(testUser.getId(), userDTO.getId());
        assertEquals(testUser.getUsername(), userDTO.getUsername());
    }

    @Test
    public void testGetMe_authenticated() throws IOException {
        TokenDTO accessToken = getAccessToken(testUser);

        ResponseEntity<UserDTO> exchange = restTemplate.exchange(meUrl, HttpMethod.GET, buildTokenHeader(accessToken, null), UserDTO.class);

        assertNotNull(exchange);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        UserDTO userDTO = exchange.getBody();
        assertNotNull(userDTO);
        assertEquals(testUser.getId(), userDTO.getId());
        assertEquals(testUser.getUsername(), userDTO.getUsername());
    }


    @Test
    public void testUpdateUser() throws IOException {

        //create
        String lastName = "user";
        String userName = getTestUserName(lastName);
        UserDTO userDTO = new UserDTO(null, userName, "test", lastName, false, 1L, "pass");
        ResponseEntity<UserDTO> user = restTemplate.postForEntity("/api/public/register", userDTO, UserDTO.class);
        UserDTO fetched = user.getBody();

        assertNotNull(fetched);
        assertEquals(userName, fetched.getUsername());
        assertTrue(fetched.isActive());
        Team team = teamDao.findOne(fetched.getId());

        User usr = new User(fetched.getUsername(), fetched.getFirstName(), fetched.getLastName(), fetched.isAdmin(), team);
        TokenDTO accessToken = getAccessToken(usr);
        assertNotNull(accessToken);
        assertNotNull(accessToken.getAccess_token());


        //update
        fetched.setActive(false);

        accessToken = getAccessToken(testUser);
        ResponseEntity<UserDTO> exchange = restTemplate.exchange(usersUrl, HttpMethod.PUT, buildTokenHeaderUserDTO(accessToken, fetched), UserDTO.class);
        fetched = exchange.getBody();
        assertFalse(fetched.isActive());

    }

}
