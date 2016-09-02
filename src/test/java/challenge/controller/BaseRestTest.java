package challenge.controller;

import challenge.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

public class BaseRestTest extends BaseDaoTest {

    RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void stub() {
        Assert.assertTrue(true);
    }

}
