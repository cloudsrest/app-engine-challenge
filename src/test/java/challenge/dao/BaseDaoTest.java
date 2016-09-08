package challenge.dao;

import challenge.dto.RecognitionTypeEnum;
import challenge.model.Recognition;
import challenge.model.Team;
import challenge.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class BaseDaoTest {

    public static final String TEST_USER_NAME = "unitTestUser";
    public static final String TEST_TEAM_NAME = "team rocket";

    public User testUser = null;


    @Autowired
    public UserDao userDao;

    @Autowired
    public TeamDao teamDao;

    @Autowired
    public RecognitionDao recognitionDao;

    @Before
    public void setup() {
        cleanup();
    }

    @After
    public void tearDown() {
        cleanup();
    }

    @Test
    public void stub() {
        assertTrue(true);
    }

    public User getUserSaved(String userName) {
        User usr = userDao.findByUsername(userName);
        if (usr == null) {
            usr = userDao.save(getUser(userName));
        }
        return usr;
    }

    public User getUser(String userName) {
        return new User(userName, "test", "user", false, getTeam());
    }


    public Recognition mockRecognition(User fromUser, String comment) {
        if (fromUser == null) {
            fromUser = getTestUser();
        }
        return new Recognition(fromUser, getUserSaved("toUser"), RecognitionTypeEnum.CREATIVITY, comment, null);
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

    public User getTestUser() {
        if (testUser == null) {
            testUser = getUserSaved(TEST_USER_NAME);
        }
        return testUser;
    }

    public void cleanup() {
        List<Recognition> all = recognitionDao.findAll();
        all.stream().filter(rec -> rec.getFromUser().getId() == getTestUser().getId()).forEach(recognitionDao::delete);
    }

}
