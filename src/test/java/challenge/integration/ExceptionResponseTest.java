package challenge.integration;

import challenge.controller.UserController;
import challenge.dto.ErrorDTO;
import challenge.dto.TokenDTO;
import challenge.service.UserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExceptionResponseTest extends BaseIntegrationTest {

    @Autowired
    UserController usrController;

    @Autowired
    UserService userService;

    String meUrl = "/api/secure/users/me";

    @Test
    public void testGetMe_with_server_exception() throws IOException {
        if (shouldNotRun()) return;

        try {
            UserService mockUsrSrv = mock(UserService.class);
            when(mockUsrSrv.getUser(testUser, testUser.getId())).thenThrow(new RuntimeException("Database not available"));
            usrController.setUserService(mockUsrSrv);
            TokenDTO accessToken = getAccessToken(testUser);

            ResponseEntity<ErrorDTO> exchange = restTemplate.exchange(meUrl, HttpMethod.GET, buildTokenHeader(accessToken, null), ErrorDTO.class);
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exchange.getStatusCode());
            ErrorDTO err = exchange.getBody();
            assertEquals("Internal Server Error", err.getError());
            assertEquals("org.springframework.web.util.NestedServletException: Request processing failed; nested exception is challenge.exception.InternalServerException: Database not available", err.getMessage());
            assertEquals(500, err.getStatus());

        } finally {
            usrController.setUserService(userService);
        }
    }

}
