package challenge.integration;

import challenge.dto.RecognitionDTO;
import challenge.dto.TokenDTO;
import challenge.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecognitionIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateRecognitionDTOs() throws IOException {
        if (shouldNotRun()) return;

        String comment1 = "great!";
        RecognitionDTO rec1 = getRecognition(comment1, testUser);
        String comment2 = "awesome!";
        RecognitionDTO rec2 = getRecognition(comment2, testUser);

        RecognitionDTO recognition = createRecognition(rec1);
        assertNotNull(recognition.getId());
        assertEquals(recognition.getComment(), comment1);

        List<RecognitionDTO> recognitions = getMyRecognitionDTOs();
        assertFalse(recognitions.isEmpty());
        assertEquals(1, recognitions.size());
        assertEquals(rec1.getComment(), recognitions.get(0).getComment());

        recognition = createRecognition(rec2);
        assertNotNull(recognition.getId());
        assertEquals(recognition.getComment(), comment2);

        recognitions = getMyRecognitionDTOs();
        assertFalse(recognitions.isEmpty());
        assertEquals(2, recognitions.size());
        assertEquals(rec2.getComment(), recognitions.get(1).getComment());
    }


    @Test
    public void testGetMine() throws IOException {
        if (shouldNotRun()) return;

        User recipient2 = getUser("someRecipient2");

        RecognitionDTO rec1 = getRecognition("Dude!", testUser);
        RecognitionDTO rec2 = getRecognition("Sweet!", testUser);
        RecognitionDTO rec3 = getRecognition("DUDE!", recipient2);

        createRecognition(rec1);
        createRecognition(rec2);
        createRecognition(rec3);

        List<RecognitionDTO> myRecognitionDTOs = getMyRecognitionDTOs();
        assertEquals(2, myRecognitionDTOs.size());
        for (RecognitionDTO myRecognitionDTO : myRecognitionDTOs) {
            assertEquals(testUser.getId(), myRecognitionDTO.getFromUserId());
        }
    }

    @Test
    public void testGetAll() throws IOException {
        if (shouldNotRun()) return;

        User recipient = getUser("someRecipient");
        User recipient2 = getUser("someRecipient2");

        RecognitionDTO rec1 = getRecognition("Dude!", recipient);
        RecognitionDTO rec2 = getRecognition("Sweet!", recipient);
        RecognitionDTO rec3 = getRecognition("DUDE!", recipient2);

        createRecognition(rec1);
        createRecognition(rec2);
        createRecognition(rec3);

        List<RecognitionDTO> myRecognitionDTOs = getAllRecognitionDTOs();
        assertTrue(myRecognitionDTOs.size() > 2);
    }

    private RecognitionDTO createRecognition(RecognitionDTO recognition) throws IOException {
        TokenDTO accessToken = getAccessToken(testUser);
        ResponseEntity<RecognitionDTO> exchange = restTemplate.exchange("/api/secure/recognitions", HttpMethod.POST, buildTokenHeader(accessToken, recognition), RecognitionDTO.class);
        return exchange.getBody();
    }

    private List<RecognitionDTO> getMyRecognitionDTOs() throws IOException {
        TokenDTO accessToken = getAccessToken(testUser);
        ResponseEntity<RecognitionDTO[]> exchange = restTemplate.exchange("/api/secure//recognitions/mine", HttpMethod.GET, buildTokenHeader(accessToken, null), RecognitionDTO[].class);
        return Arrays.asList(exchange.getBody());
    }

    private List<RecognitionDTO> getAllRecognitionDTOs() throws IOException {
        TokenDTO accessToken = getAccessToken(testUser);
        ResponseEntity<RecognitionDTO[]> exchange = restTemplate.exchange("/api/secure//recognitions/all", HttpMethod.GET, buildTokenHeader(accessToken, null), RecognitionDTO[].class);
        return Arrays.asList(exchange.getBody());
    }

}
