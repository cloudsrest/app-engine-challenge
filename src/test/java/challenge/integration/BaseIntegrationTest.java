package challenge.integration;

import challenge.BaseTest;
import challenge.dto.ErrorDTO;
import challenge.dto.RecognitionDTO;
import challenge.dto.TokenDTO;
import challenge.model.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest extends BaseTest {

    @Autowired
    public TestRestTemplate restTemplate;

    protected boolean shouldNotRun() {
        System.out.println("System.getProperties().getProperty(\"noIntegration\") = " + System.getProperties().getProperty("noIntegration"));

        if (null != System.getProperties().getProperty("noIntegration")) {
            System.out.println("Setting doNotRun for BaseIntegrationTest");
            return true;
        }

        return false;
    }

    public TokenDTO getAccessToken(User usr) throws IOException {
        String authUrl;
        if (usr == null) {
            authUrl = "/api/oauth/token?username=admin&password=admin&grant_type=password";
        } else {
            authUrl = "/api/oauth/token?username=" + usr.getUsername() + "&password=pass&grant_type=password";
        }
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

    public HttpEntity<RecognitionDTO> buildTokenHeader(TokenDTO accessToken, RecognitionDTO obj) {
        if (obj == null) {
            obj = new RecognitionDTO();
        }
        String bodyTesting = "test";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(obj, headers);
    }



    public void assertUnauthorized(ResponseEntity<String> forEntity) throws IOException {
        assertNotNull(forEntity);
        assertEquals(forEntity.getStatusCode(), HttpStatus.UNAUTHORIZED);
        String body = forEntity.getBody();
        ErrorDTO error = mapper.readValue(body, ErrorDTO.class);
        assertEquals("Access Denied", error.getMessage());
        assertEquals("Unauthorized", error.getError());
        assertEquals(401, error.getStatus());
    }



}
