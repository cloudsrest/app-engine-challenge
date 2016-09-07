package challenge.integration;
import challenge.controller.HealthController;
import challenge.dto.Health;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void health() {
        ResponseEntity<Health> entity =
                restTemplate.getForEntity("/api/public/health", Health.class);
        assertTrue(entity.getStatusCode().is2xxSuccessful());
        Assert.assertEquals(entity.getBody().getStatus(), HealthController.STATUS);
    }
}