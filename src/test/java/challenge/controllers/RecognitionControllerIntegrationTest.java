package challenge.controllers;

import challenge.Application;
import challenge.dto.RecognitionDTO;
import challenge.dto.RecognitionTypeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class RecognitionControllerIntegrationTest extends BaseRestTest {

//    @Before
//    public void setup() {
//        deleteAllRecognitions();
//    }
//
//    @After
//    public void tearDown() {
//        deleteAllRecognitions();
//    }

    @Test
    public void testCreateRecognitions() throws JsonProcessingException {
//        Recognition recognition1 = new Recognition("111","222", RecognitionTypeEnum.CREATIVITY.toString(),"xxx");
//        Recognition recognition2 = new Recognition("333","444", RecognitionTypeEnum.HARD_WORK.toString(),"iii");
//
//        createRecognition(recognition1);
//
//        Recognition[] forNow = restTemplate.getForObject("http://localhost:9000/recognitions", Recognition[].class);
//        List<Recognition> recognitions = Arrays.asList(forNow);
//        assertFalse(recognitions.isEmpty());
//        assertEquals(1, recognitions.size());
//        assertEquals(recognition1, recognitions.get(0));
//
//        createRecognition(recognition2);
//
//        recognitions = getRecognitions();
//        assertFalse(recognitions.isEmpty());
//        assertEquals(2, recognitions.size());
//        assertEquals(recognition2, recognitions.get(1));
//
//        deleteAllRecognitions();
//
//        recognitions = getRecognitions();
//        assertTrue(recognitions.isEmpty());
    }

//    private void createRecognition(Recognition recognition2) {
//        restTemplate.postForEntity("http://localhost:9000/recognitions", recognition2, String.class);
//    }
//
//    private void deleteAllRecognitions() {
//        restTemplate.delete("http://localhost:9000/recognitions");
//    }
//
//    private List<Recognition> getRecognitions() {
//        return Arrays.asList(restTemplate.getForObject("http://localhost:9000/recognitions", Recognition[].class));
//    }

}
