package challenge;

import challenge.dao.RecognitionDao;
import challenge.dao.TeamDao;
import challenge.dao.UserDao;
import challenge.dto.ErrorDTO;
import challenge.dto.RecognitionDTO;
import challenge.dto.RecognitionTypeEnum;
import challenge.dto.TokenDTO;
import challenge.model.Recognition;
import challenge.model.Team;
import challenge.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class BaseTest {

    public static final String TEST_USER_NAME = "unitTestUser";
    public static final String TEST_TEAM_NAME = "team rocket";
    public static final String TEST_USER_PREFIX = "UNIT_TEST-";

    public User testUser = null;
    public ObjectMapper mapper = new ObjectMapper();


    @Autowired
    public UserDao userDao;

    @Autowired
    public TeamDao teamDao;

    @Autowired
    public RecognitionDao recognitionDao;

    @Before
    public void setup() {
        cleanup();
        testUser = getUser(TEST_USER_NAME);
    }

    @After
    public void tearDown() {
        cleanup();
    }

    @Test
    public void stub() {
        assertTrue(true);
    }

    public Team getTeam() {
        List<Team> all = teamDao.findAll();
        if (all != null && !all.isEmpty()) {
            return all.get(0);
        }

        Team team = new Team();
        team.setName(TEST_TEAM_NAME);
        return teamDao.save(team);
    }

    public String getTestUserName(String userName) {
        Random random = new Random(10000);
        return TEST_USER_PREFIX + "-" + random.nextInt() + "-" + userName;
    }

    public User getUser(String userName) {
        String s = getTestUserName(userName);
        User usr = userDao.findByUsername(s);
        if (usr == null) {
            usr = new User(s, "test", "user", false, getTeam());
            usr.setPassword("fc98531156e57454696428d1f6dd6bb68eea90353992b67c3b719be52a26200074d1108ae1b064d8");
            usr = userDao.save(usr);
        }
        return usr;
    }

    public void cleanup() {
        List<Recognition> allRecognitions = recognitionDao.findAll();
        allRecognitions.stream().filter(this::isTestAccount).forEach(recognitionDao::delete);

        List<User> allUsers = userDao.findAll();
        allUsers.stream().filter(usr -> usr.getUsername().contains(TEST_USER_PREFIX)).forEach(userDao::delete);
    }

    public boolean isTestAccount(Recognition rec) {
        User fromUser = rec.getFromUser();
        return testUser == null || testUser.getId() == null || fromUser == null || fromUser.getId() == null || fromUser.getId().equals(testUser.getId()) || fromUser.getUsername() == null || fromUser.getUsername().contains(TEST_USER_PREFIX);
    }

    public RecognitionDTO getRecognition(String comment, User toUser) {
        Recognition recognition = new Recognition(testUser, toUser, RecognitionTypeEnum.DELIVERY, comment, new Date());
        return new RecognitionDTO(recognition);
    }

}
