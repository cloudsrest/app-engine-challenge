package challenge.dao;

import challenge.BaseDaoTest;
import challenge.dto.RecognitionTypeEnum;
import challenge.model.Recognition;
import challenge.model.User;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testRecognitionsMine() {
        User fromUsr1 = getUserSaved("fromUsr");
        User fromUsr2 = getUserSaved("fromUsr");

        User toUsr = getUserSaved("toUsr");

        recognitionDao.save(new Recognition(fromUsr1, toUsr, RecognitionTypeEnum.CREATIVITY, "owner comment 1", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUsr, RecognitionTypeEnum.CREATIVITY, "owner comment 2", new Date()));
        recognitionDao.save(new Recognition(fromUsr2, toUsr, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));

        List<Recognition> byToUser = recognitionDao.findByFromUser(fromUsr1);
        assertEquals(2, byToUser.size());
    }

    @Test
    public void testRecognitionsAll() {
        User fromUsr1 = getUserSaved("fromUsr");
        User fromUsr2 = getUserSaved("fromUsr");

        User toUsr = getUserSaved("toUsr");

        recognitionDao.save(new Recognition(fromUsr1, toUsr, RecognitionTypeEnum.CREATIVITY, "owner comment 1", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUsr, RecognitionTypeEnum.CREATIVITY, "owner comment 2", new Date()));
        recognitionDao.save(new Recognition(fromUsr2, toUsr, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));

        List<Recognition> byToUser = recognitionDao.findAll();
        assertTrue(byToUser.size() > 2);
    }

}
