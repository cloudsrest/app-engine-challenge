package challenge.controllers;

import challenge.Application;
import challenge.dto.Recognition;
import challenge.dto.RecognitionTypeEnum;
import challenge.dto.User;
import challenge.services.RecognitionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RecognitionControllerTest {

    @Autowired
    RecognitionService recognitionService;

    @Autowired
    RecognitionController recognitionController;

    @Test
    public void testCreateRecognition() {
        String fromUserKey = "123";
        recognitionService.createRecognition(new User(), new Recognition(fromUserKey, "222", RecognitionTypeEnum.CREATIVITY.toString(), "good job", "product demo"));

        List<Recognition> recognitions = recognitionController.getRecognitions();
        assertNotNull(recognitions);
    }

}
