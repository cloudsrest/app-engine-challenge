package challenge.controller;

import challenge.Application;
import challenge.dao.RecognitionDao;
import challenge.dto.RecognitionDTO;
import challenge.dto.RecognitionTypeEnum;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9002")
public class RecognitionControllerIntegrationTest extends BaseRestTest {

    public static final String BASE_RECOGNITION_URL = "http://localhost:9002/recognitions";

    @Before
    public void setup() {
        testUser = getTestUser();
        cleanUp();
    }

    @After
    public void tearDown() {
        cleanUp();
    }

    @Test
    public void testCreateRecognitionDTOs() throws JsonProcessingException {
        String comment1 = "great!";
        RecognitionDTO RecognitionDTO1 = new RecognitionDTO(mockRecognition(null, comment1));
        String comment2 = "awesome!";
        RecognitionDTO RecognitionDTO2 = new RecognitionDTO(mockRecognition(null, comment2));

        RecognitionDTO recognition = createRecognition(RecognitionDTO1);
        assertNotNull(recognition.getId());
        assertEquals(recognition.getComment(), comment1);

        List<RecognitionDTO> recognitions = getMyRecognitionDTOs();
        assertFalse(recognitions.isEmpty());
        assertEquals(1, recognitions.size());
        assertEquals(RecognitionDTO1.getComment(), recognitions.get(0).getComment());

        recognition = createRecognition(RecognitionDTO2);
        assertNotNull(recognition.getId());
        assertEquals(recognition.getComment(), comment2);

        recognitions = getMyRecognitionDTOs();
        assertFalse(recognitions.isEmpty());
        assertEquals(2, recognitions.size());
        assertEquals(RecognitionDTO2.getComment(), recognitions.get(1).getComment());

        cleanUp();
        recognitions = getMyRecognitionDTOs();
        assertTrue(recognitions.isEmpty());
    }


    @Test
    public void testGetMine()  {
        RecognitionDTO RecognitionDTO1 = new RecognitionDTO(mockRecognition(null, "dude!"));
        RecognitionDTO RecognitionDTO2 = new RecognitionDTO(mockRecognition(null, "sweet!"));
        RecognitionDTO RecognitionDTO3 = new RecognitionDTO(mockRecognition(getUserSaved("yup"), "dude!"));

        createRecognition(RecognitionDTO1);
        createRecognition(RecognitionDTO2);
        createRecognition(RecognitionDTO3);

        List<RecognitionDTO> myRecognitionDTOs = getMyRecognitionDTOs();
        assertEquals(2, myRecognitionDTOs.size());
        for (RecognitionDTO myRecognitionDTO : myRecognitionDTOs) {
            assertEquals(new Long(testUser.getId()), myRecognitionDTO.getFromUserId());
        }
    }

    @Test
    public void testGetAll()  {
        RecognitionDTO RecognitionDTO1 = new RecognitionDTO(mockRecognition(null, "dude!"));
        RecognitionDTO RecognitionDTO2 = new RecognitionDTO(mockRecognition(null, "sweet!"));
        RecognitionDTO RecognitionDTO3 = new RecognitionDTO(mockRecognition(getUserSaved("yup"), "dude!"));

        createRecognition(RecognitionDTO1);
        createRecognition(RecognitionDTO2);
        createRecognition(RecognitionDTO3);

        List<RecognitionDTO> myRecognitionDTOs = getAllRecognitionDTOs();
        assertTrue(myRecognitionDTOs.size() > 2);
    }

    private RecognitionDTO createRecognition(RecognitionDTO recognition) {
        //restTemplate.postForEntity(BASE_RECOGNITION_URL, recognition, RecognitionDTO.class);
        return restTemplate.postForObject(BASE_RECOGNITION_URL, recognition, RecognitionDTO.class);
    }

    private List<RecognitionDTO> getMyRecognitionDTOs() {
        return Arrays.asList(restTemplate.getForObject(BASE_RECOGNITION_URL + "/mine", RecognitionDTO[].class));
    }

    private List<RecognitionDTO> getAllRecognitionDTOs() {
        return Arrays.asList(restTemplate.getForObject(BASE_RECOGNITION_URL + "/all", RecognitionDTO[].class));
    }

    private void cleanUp() {
        List<Recognition> allRecognitions = recognitionDao.findAll();
        if (allRecognitions!=null) {
            allRecognitions.stream().filter(s -> {
                System.out.println(s);
                System.out.println(testUser);
                return s.getFromUser().getId() == (testUser.getId());
            }).forEach(recognitionDao::delete);
        }
    }

}
