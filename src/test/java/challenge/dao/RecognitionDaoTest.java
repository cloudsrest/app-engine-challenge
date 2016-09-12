package challenge.dao;

import challenge.BaseTest;
import challenge.dto.RecognitionTypeEnum;
import challenge.model.Recognition;
import challenge.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Ignore
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

}
