package challenge.controller;

import challenge.dto.Health;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HealthControllerTest {

    @Test
    public void testHealth() {
        HealthController healthController = new HealthController();
        Health health = healthController.health();
        assertEquals(health.getStatus(), HealthController.STATUS);
    }

}
