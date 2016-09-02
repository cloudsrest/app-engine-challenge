package challenge.controller;

import challenge.Application;
import challenge.BaseDaoTest;
import challenge.dto.RecognitionDTO;
import challenge.dto.RecognitionTypeEnum;
import challenge.dto.UserDTO;
import challenge.service.RecognitionService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RecognitionControllerTest extends BaseDaoTest {

    @Test
    public void testCreateRecognition() {
        String comment = "way to go - party time!";
        recognitionController.createRecognition(new RecognitionDTO(mockRecognition(getTestUser(), comment)));

        recognitionController.setRequester(getTestUser());
        List<RecognitionDTO> recognitions = recognitionController.getMyRecognitions();

        assertNotNull(recognitions);
        assertEquals(1, recognitions.size());
        RecognitionDTO recognitionDTO = recognitions.get(0);

        assertEquals(comment, recognitionDTO.getComment());
        assertEquals(new Long(getTestUser().getId()), recognitionDTO.getFromUserId());
    }

}
