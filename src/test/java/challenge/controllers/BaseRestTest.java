package challenge.controllers;

import org.springframework.boot.test.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

public class BaseRestTest {

   RestTemplate restTemplate = new TestRestTemplate();

}
