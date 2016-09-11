package challenge.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HealthDTO_test {

    @Test
    public void test() {
        Health health = new Health();
        health.setStatus("hello!");

        assertEquals("hello!", health.getStatus());
        assertNotNull(health.toString());
    }

}
