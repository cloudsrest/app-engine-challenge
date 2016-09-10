package challenge.controller;

import challenge.exception.AuthorizationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AspectTest {

    @Autowired
    UserController userController;

    @Test(expected = AuthorizationException.class)
    public void testInvalidRequest() {
        OAuth2Authentication mock = mock(OAuth2Authentication.class);
        Authentication authentication = mock(Authentication.class);
        when(mock.getUserAuthentication()).thenReturn(authentication);
        when(authentication.getAuthorities()).thenReturn(Collections.EMPTY_LIST);

        userController.getUsers(mock);
    }

}
