package challenge.dao;

import challenge.dto.RecognitionTypeEnum;
import challenge.model.Recognition;
import challenge.model.User;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RecognitionDaoTest extends BaseDaoTest {

    @Test
    public void testSaveRecognition() {
        User toUsr = getUserSaved("toUsr");
        User fromUsr = getUserSaved("fromUsr");
        String comment = "good job!";
        Recognition recognition = new Recognition(toUsr, fromUsr, RecognitionTypeEnum.CREATIVITY, comment, new Date());

        Recognition saved = recognitionDao.save(recognition);

        assertNotNull(saved);
        assertEquals(comment, saved.getComment());
    }

}
