package challenge.dao;

import challenge.BaseTest;
import challenge.dto.RecognitionTypeEnum;
import challenge.model.Recognition;
import challenge.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class RecognitionDaoTest extends BaseTest {

    @Test
    public void testSaveRecognition() {
        User toUsr = getUser("toUsr");
        User fromUsr = getUser("fromUsr");
        String comment = "good job!";
        Recognition recognition = new Recognition(toUsr, fromUsr, RecognitionTypeEnum.CREATIVITY, comment, new Date());

        Recognition saved = recognitionDao.save(recognition);

        assertNotNull(saved);
        assertEquals(comment, saved.getComment());
    }

    @Test
    public void testRecognitionsMine() {
        User fromUsr1 = getUser("fromUsr1");
        User fromUsr2 = getUser("fromUsr2");
        User toUsr = getUser("toUsr");
        recognitionDao.save(new Recognition(fromUsr1, toUsr, RecognitionTypeEnum.CREATIVITY, "owner comment 1", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUsr, RecognitionTypeEnum.CREATIVITY, "owner comment 2", new Date()));
        recognitionDao.save(new Recognition(fromUsr2, toUsr, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));

        List<Recognition> byToUser = recognitionDao.findByFromUser(fromUsr1);

        assertEquals(2, byToUser.size());
    }

    @Test
    public void testRecognitionsAll() {
        User fromUsr1 = getUser("fromUsr");
        User fromUsr2 = getUser("fromUsr");

        User toUsr = getUser("toUsr");

        recognitionDao.save(new Recognition(fromUsr1, toUsr, RecognitionTypeEnum.CREATIVITY, "owner comment 1", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUsr, RecognitionTypeEnum.CREATIVITY, "owner comment 2", new Date()));
        recognitionDao.save(new Recognition(fromUsr2, toUsr, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));

        List<Recognition> byToUser = recognitionDao.findAll();
        assertTrue(byToUser.size() > 2);
    }

    @Test
    public void testGetRecCount() {
        User fromUsr = getUser("fromUsr2");
        User toUsr = getUser("toUsr");
        recognitionDao.save(new Recognition(fromUsr, toUsr, RecognitionTypeEnum.CREATIVITY, "owner comment 1", new Date()));
        recognitionDao.save(new Recognition(fromUsr, toUsr, RecognitionTypeEnum.CREATIVITY, "owner comment 2", new Date()));
        recognitionDao.save(new Recognition(fromUsr, toUsr, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));

        long i = recognitionDao.totalRecognitions();
        assertEquals(3, i);

    }

    @Test
    public void testTopReceivers() {
        User fromUsr1 = getUser("fromUsr");
        User toUser1 = getUser("fromUsr1");
        User toUser2 = getUser("fromUs2");
        User toUser3 = getUser("fromUs3");

        recognitionDao.save(new Recognition(fromUsr1, toUser1, RecognitionTypeEnum.CREATIVITY, "owner comment 1", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUser2, RecognitionTypeEnum.CREATIVITY, "owner comment 2", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUser2, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));

        recognitionDao.save(new Recognition(fromUsr1, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));
        recognitionDao.save(new Recognition(fromUsr1, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));


        List<RecognitionSummary> o = recognitionDao.topRecognitionReceivers();
        assertEquals(3, o.size());
        RecognitionSummary recognitionSummary = o.get(0);
        assertEquals(toUser3.getUsername(), recognitionSummary.getUserName());
        assertEquals(6, recognitionSummary.getCount());
    }

}
