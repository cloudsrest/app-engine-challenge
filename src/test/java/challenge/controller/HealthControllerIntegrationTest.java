package challenge.controller;

import challenge.Application;
import challenge.dto.Health;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9001")
public class HealthControllerIntegrationTest {

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void health() {
        ResponseEntity<Health> entity =
                restTemplate.getForEntity("http://localhost:9001/health", Health.class);

        assertTrue(entity.getStatusCode().is2xxSuccessful());
        assertEquals(entity.getBody().getStatus(), HealthController.STATUS);
    }

}
