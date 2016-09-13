package challenge.integration;

import challenge.dao.RecognitionSummary;
import challenge.dto.RecognitionTypeEnum;
import challenge.dto.Summary;
import challenge.dto.TokenDTO;
import challenge.model.Recognition;
import challenge.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SummaryIntegrationTest extends BaseIntegrationTest {

    String summaryUrl = "/api/secure/summary/stats";

    @Test
    public void testGetStats() throws IOException {
        setupData();
        TokenDTO accessToken = getAccessToken(testUser);
        ResponseEntity<Summary> exchange = restTemplate.exchange(summaryUrl, HttpMethod.GET, buildTokenHeader(accessToken, null), Summary.class);
        Summary summary = exchange.getBody();

        assertTrue(summary.getRecognitionCount() > 8);
        assertTrue(summary.getUserCount() > 3);
        List<RecognitionSummary> summaries = summary.getTopRecognitionReceivers();
        assertEquals(3, summaries.size());
        RecognitionSummary topSummary = summaries.get(0);
        assertTrue(topSummary.getCount() > 5);
    }

    private void setupData() {
        User toUser1 = getUser("fromUsr1");
        User toUser2 = getUser("fromUs2");
        User toUser3 = getUser("fromUs3");

        recognitionDao.save(new Recognition(testUser, toUser1, RecognitionTypeEnum.DELIVERY, "owner comment 1", new Date()));

        recognitionDao.save(new Recognition(testUser, toUser2, RecognitionTypeEnum.DELIVERY, "owner comment 2", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser2, RecognitionTypeEnum.DELIVERY, "from different user", new Date()));

        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.DELIVERY, "from different user", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.DELIVERY, "from different user", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.DELIVERY, "from different user", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.DELIVERY, "from different user", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.DELIVERY, "from different user", new Date()));
        recognitionDao.save(new Recognition(testUser, toUser3, RecognitionTypeEnum.DELIVERY, "from different user", new Date()));
    }
}
