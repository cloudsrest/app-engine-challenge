package challenge.controller;

import challenge.BaseTest;
import challenge.dto.RecognitionTypeEnum;
import challenge.dto.Summary;
import challenge.model.Recognition;
import challenge.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SummaryControllerTest extends BaseTest {

    @Autowired
    SummaryController summaryController;

    @Test
    public void testGetStats() {
        Principal mockPrinciple = mock(Principal.class);

        User toUser1 = getUser("fromUsr1");
        User toUser2 = getUser("fromUs2");
        User toUser3 = getUser("fromUs3");

        recognitionDao.save(new Recognition(testUser, toUser1, RecognitionTypeEnum.CREATIVITY, "owner comment 1", new Date()));

        recognitionDao.save(new Recognition(testUser, toUser2, RecognitionTypeEnum.CREATIVITY, "owner comment 2", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser2, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));

        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.CREATIVITY, "from different user", new Date()));

        Summary stats = summaryController.getStats(mockPrinciple);
        assertTrue(stats.getUserCount() > 3);
        assertTrue(stats.getRecognitionCount() > 8);
        assertEquals(3, stats.getTopRecognitionReceivers().size());
    }

}
