package challenge.integration;

import challenge.dto.ErrorDTO;
import challenge.dto.TokenDTO;
import challenge.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    String usersUrl = "/api/secure/users";
    String userUrl = "/api/secure/users/1";
    String meUrl = "/api/secure/users/me";

    @Autowired
    private TestRestTemplate restTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetUsers_not_authenticated() throws IOException {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(usersUrl, String.class);

        assertUnauthorized(forEntity);
    }

    @Test
    public void testGetUser_not_authenticated() throws IOException {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(userUrl, String.class);

        assertUnauthorized(forEntity);
    }

    @Test
    public void testGetMe_not_authenticated() throws IOException {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(meUrl, String.class);

        assertUnauthorized(forEntity);
    }

    @Test
    public void testGetUsers_authenticated() throws IOException {
        TokenDTO accessToken = getAccessToken();

        ResponseEntity<UserDTO[]> exchange = restTemplate.exchange(usersUrl, HttpMethod.GET, buildTokenHeader(accessToken), UserDTO[].class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        List<UserDTO> userDTOs = Arrays.asList(exchange.getBody());
        assertNotNull(userDTOs);
        assertTrue(userDTOs.size() > 2);

        UserDTO admin = userDTOs.stream().filter(user -> user.getUsername().equals("admin")).findFirst().get();
        assertNotNull(admin);
        assertEquals("admin", admin.getUsername());
    }

    @Test
    public void testGetUser_authenticated() throws IOException {
        TokenDTO accessToken = getAccessToken();

        ResponseEntity<UserDTO> exchange = restTemplate.exchange(userUrl, HttpMethod.GET, buildTokenHeader(accessToken), UserDTO.class);

        assertNotNull(exchange);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        UserDTO userDTO = exchange.getBody();
        assertNotNull(userDTO);
        assertEquals(new Long(1), userDTO.getId());
        assertEquals("admin", userDTO.getUsername());
    }

    @Test
    public void testGetMe_authenticated() throws IOException {
        TokenDTO accessToken = getAccessToken();

        ResponseEntity<UserDTO> exchange = restTemplate.exchange(meUrl, HttpMethod.GET, buildTokenHeader(accessToken), UserDTO.class);

        assertNotNull(exchange);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        UserDTO userDTO = exchange.getBody();
        assertNotNull(userDTO);
        assertEquals(new Long(1), userDTO.getId());
        assertEquals("admin", userDTO.getUsername());
    }

    private HttpEntity<String> buildTokenHeader(TokenDTO accessToken) {
        String bodyTesting = "test";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken.getAccess_token());
        return new HttpEntity<>(bodyTesting, headers);
    }

    private TokenDTO getAccessToken() throws IOException {
        String authUrl = "/api/oauth/token?username=admin&password=admin&grant_type=password";
        String bodyTesting = "test";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic Zmxhc2hkZXY6c2VjcmV0");
        HttpEntity<String> entity = new HttpEntity<>(bodyTesting, headers);

        ResponseEntity<String> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, entity, String.class);

        assertNotNull(exchange);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        TokenDTO token = mapper.readValue(exchange.getBody(), TokenDTO.class);
        assertEquals("bearer", token.getToken_type());
        assertNotNull(token.getAccess_token());
        return token;
    }

    private void assertUnauthorized(ResponseEntity<String> forEntity) throws IOException {
        assertNotNull(forEntity);
        assertEquals(forEntity.getStatusCode(), HttpStatus.UNAUTHORIZED);
        String body = forEntity.getBody();
        ErrorDTO error = mapper.readValue(body, ErrorDTO.class);
        assertEquals("Access Denied", error.getMessage());
        assertEquals("Unauthorized", error.getError());
        assertEquals(401, error.getStatus());
    }

}
