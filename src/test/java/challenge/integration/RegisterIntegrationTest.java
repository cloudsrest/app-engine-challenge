package challenge.integration;

import challenge.dao.TeamDao;
import challenge.dto.RecognitionDTO;
import challenge.dto.TokenDTO;
import challenge.dto.UserDTO;
import challenge.model.Team;
import challenge.model.User;
import challenge.service.TeamService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterIntegrationTest extends BaseIntegrationTest {

    @Autowired
    TeamDao teamDao;

    @Test
    public void testRegister() throws IOException {

        String userName = getTestUserName("user");
        UserDTO userDTO = new UserDTO(null, userName, "test", "user", false, 1L, "pass");
        ResponseEntity<UserDTO> user = restTemplate.postForEntity("/api/public/register", userDTO, UserDTO.class);
        UserDTO fetched = user.getBody();

        assertNotNull(fetched);
        assertEquals(userName, fetched.getUsername());
        Team team = teamDao.findOne(fetched.getId());

        User usr = new User(fetched.getUsername(), fetched.getFirstName(), fetched.getLastName(), fetched.isAdmin(), team);
        TokenDTO accessToken = getAccessToken(usr);
        assertNotNull(accessToken);
        assertNotNull(accessToken.getAccess_token());
    }

}
