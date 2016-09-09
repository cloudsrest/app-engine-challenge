package challenge.controller;

import challenge.dao.BaseDaoTest;
import challenge.dto.RecognitionDTO;
import challenge.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class RecognitionControllerTest extends BaseDaoTest {

    @Autowired
    public RecognitionController recognitionController;

    @Test
    public void testCreateRecognition() {
        String comment = "way to go - party time!";
        Principal mockPrinciple = mock(Principal.class);
        User testUser = getTestUser();
        when(mockPrinciple.getName()).thenReturn(testUser.getUsername());

        RecognitionDTO recognition = new RecognitionDTO(mockRecognition(getTestUser(), comment));
        recognitionController.createRecognition(recognition, mockPrinciple);

        recognitionController.setRequester(getTestUser());
        List<RecognitionDTO> recognitions = recognitionController.getMyRecognitions(mockPrinciple);

        assertNotNull(recognitions);
        assertEquals(1, recognitions.size());
        RecognitionDTO recognitionDTO = recognitions.get(0);
        assertEquals(comment, recognitionDTO.getComment());
        assertEquals(new Long(getTestUser().getId()), recognitionDTO.getFromUserId());
    }

}
