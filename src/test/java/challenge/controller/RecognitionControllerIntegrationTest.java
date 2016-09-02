package challenge.controller;

import challenge.Application;
import challenge.dao.UserDao;
import challenge.dto.RecognitionDTO;
import challenge.model.Recognition;
import challenge.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class RecognitionControllerIntegrationTest extends BaseRestTest {

    @Autowired
    private UserDao userDao;

    @Before
    public void setup() {
        deleteAllRecognitionDTOs();
    }

    @After
    public void tearDown() {
        deleteAllRecognitionDTOs();
    }

    @Test
    public void testCreateRecognitionDTOs() throws JsonProcessingException {
        RecognitionDTO RecognitionDTO1 = new RecognitionDTO(mockRecognition());
        RecognitionDTO RecognitionDTO2 = new RecognitionDTO(mockRecognition());

        createRecognitionDTO(RecognitionDTO1);

        RecognitionDTO[] forNow = restTemplate.getForObject("http://localhost:9000/RecognitionDTOs", RecognitionDTO[].class);
        List<RecognitionDTO> RecognitionDTOs = Arrays.asList(forNow);
        assertFalse(RecognitionDTOs.isEmpty());
        assertEquals(1, RecognitionDTOs.size());
        assertEquals(RecognitionDTO1, RecognitionDTOs.get(0));

        createRecognitionDTO(RecognitionDTO2);

        RecognitionDTOs = getRecognitionDTOs();
        assertFalse(RecognitionDTOs.isEmpty());
        assertEquals(2, RecognitionDTOs.size());
        assertEquals(RecognitionDTO2, RecognitionDTOs.get(1));

        deleteAllRecognitionDTOs();

        RecognitionDTOs = getRecognitionDTOs();
        assertTrue(RecognitionDTOs.isEmpty());
    }

    private void createRecognitionDTO(RecognitionDTO RecognitionDTO2) {
        restTemplate.postForEntity("http://localhost:9000/RecognitionDTOs", RecognitionDTO2, String.class);
    }

    private void deleteAllRecognitionDTOs() {
        restTemplate.delete("http://localhost:9000/RecognitionDTOs");
    }

    private List<RecognitionDTO> getRecognitionDTOs() {
        return Arrays.asList(restTemplate.getForObject("http://localhost:9000/RecognitionDTOs", RecognitionDTO[].class));
    }

    private Recognition mockRecognition() {
        Random random = new Random();
        int index = random.nextInt(1000);

        User fromUser = new User();
        User toUser = new User();
        return new Recognition();
    }

}
