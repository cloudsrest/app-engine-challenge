package challenge.integration;

import challenge.controller.UserController;
import challenge.dto.ErrorDTO;
import challenge.dto.TokenDTO;
import challenge.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExceptionResponseTest extends BaseIntegrationTest {

    @Autowired
    UserController usrController;

    String meUrl = "/api/secure/users/me";

    @Test
    public void testGetMe_with_server_exception() throws IOException {
        UserService userService = mock(UserService.class);
        when(userService.getUser(testUser, testUser.getId())).thenThrow(new RuntimeException("Database not available"));
        usrController.setUserService(userService);
        TokenDTO accessToken = getAccessToken(testUser);

        ResponseEntity<ErrorDTO> exchange = restTemplate.exchange(meUrl, HttpMethod.GET, buildTokenHeader(accessToken, null), ErrorDTO.class);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exchange.getStatusCode());
        Assert.assertEquals("Internal Server Error", exchange.getBody().getError());
    }

}
