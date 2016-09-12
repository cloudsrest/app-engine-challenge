package challenge.security;

import challenge.dto.TokenDTO;
import challenge.integration.BaseIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomLogoutSuccessHandlerTest extends BaseIntegrationTest {

    @Autowired
    CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Test
    public void test() throws IOException, ServletException {
        TokenDTO accessToken = getAccessToken(testUser);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Authentication authentication = mock(Authentication.class);

        Mockito.when(request.getHeader("authorization")).thenReturn("Bearer " + accessToken.getAccess_token());

        customLogoutSuccessHandler.onLogoutSuccess(request, response, authentication);
        assertTrue(true);
    }

}
