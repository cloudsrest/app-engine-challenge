package challenge.controller;

import challenge.BaseTest;
import challenge.dto.RecognitionDTO;
import challenge.dto.RecognitionTypeEnum;
import challenge.model.Recognition;
import challenge.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class RecognitionControllerTest extends BaseTest {

    @Autowired
    public RecognitionController recognitionController;

    @Test
    public void testCreateRecognition() {
        User toUser = getUser("toUser");
        Principal mockPrinciple = mock(Principal.class);
        when(mockPrinciple.getName()).thenReturn(toUser.getUsername());
        String comment = "way to go - party time!";
        RecognitionDTO recognition = getRecognition(comment, toUser);

        recognitionController.createRecognition(recognition, mockPrinciple);

        List<RecognitionDTO> recognitions = recognitionController.getMyRecognitions(mockPrinciple);
        assertNotNull(recognitions);
        assertEquals(1, recognitions.size());
        RecognitionDTO recognitionDTO = recognitions.get(0);
        assertEquals(comment, recognitionDTO.getComment());
        assertEquals(new Long(testUser.getId()), recognitionDTO.getFromUserId());
    }

    @Test
    public void testGetMyRecognitions() {
        Principal mockPrinciple = mock(Principal.class);
        User myUser = getUser("toUser3");
        when(mockPrinciple.getName()).thenReturn(myUser.getUsername());
        String comment = "way to go - party time!";
        setUpTestRecognitions(mockPrinciple, myUser, comment);

        List<RecognitionDTO> recognitions = recognitionController.getMyRecognitions(mockPrinciple);
        assertEquals(3, recognitions.size());
        recognitions.stream().forEach(rec -> {
                    assertEquals(testUser.getId(), rec.getFromUserId());
                    assertEquals(comment, rec.getComment());
                }
        );
    }

    @Test
    public void testGetAllRecognitions() {
        Principal mockPrinciple = mock(Principal.class);
        User myUser = getUser("toUser3");
        when(mockPrinciple.getName()).thenReturn(myUser.getUsername());
        String comment = "way to go - party time!";
        setUpTestRecognitions(mockPrinciple, myUser, comment);

        List<RecognitionDTO> recognitions = recognitionController.getAllRecognitions(mockPrinciple);
        assertTrue(recognitions.size() > 4);
    }

    private void setUpTestRecognitions(Principal mockPrinciple, User myUser, String comment) {
        RecognitionDTO recognition1 = getRecognition(comment, getUser("toUser1"));
        RecognitionDTO recognition2 = getRecognition(comment, getUser("toUser2"));
        RecognitionDTO recognition3 = getRecognition(comment, myUser);
        RecognitionDTO recognition4 = getRecognition(comment, myUser);
        RecognitionDTO recognition5 = getRecognition(comment, myUser);

        recognitionController.createRecognition(recognition1, mockPrinciple);
        recognitionController.createRecognition(recognition2, mockPrinciple);
        recognitionController.createRecognition(recognition3, mockPrinciple);
        recognitionController.createRecognition(recognition4, mockPrinciple);
        recognitionController.createRecognition(recognition5, mockPrinciple);
    }

}
