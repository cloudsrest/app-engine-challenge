package challenge.dao;

import challenge.Application;
import challenge.model.Recognition;
import challenge.model.Team;
import challenge.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseDaoTest {

    public static final String TEST_TEAM_NAME = "unitTest";
    public static final String TEST_USR_PREFIX = "unitTest-";


    @Autowired
    UserDao userDao;

    @Autowired
    TeamDao teamDao;

    @Autowired
    RecognitionDao recognitionDao;

    @Before
    public void setup() {
        cleanup();
    }

    @After
    public void tearDown() {
        cleanup();
    }

    User getUserSaved(String user) {
        return userDao.save(getUser(user));
    }

    User getUser(String user) {
        return new User(TEST_USR_PREFIX + user, "test", "user", false, getTeam());
    }

    void cleanup() {
        List<Recognition> allRecognitions = recognitionDao.findAll();
        for (Recognition recognition : allRecognitions) {
            if (recognition.getFromUser().getUsername().toLowerCase().contains(TEST_USR_PREFIX.toLowerCase())) {
                recognitionDao.delete(recognition);
            }
        }

        List<User> allUser = userDao.findAll();
        allUser.stream().filter(user -> user.getUsername().toLowerCase().contains("unit")).forEach(userDao::delete);
    }

    Team getTeam() {
        List<Team> all = teamDao.findAll();
        if (all!=null && !all.isEmpty()) {
            return all.get(0);
        }

        Team team = new Team();

        team.setName(TEST_TEAM_NAME);
        return teamDao.save(team);
    }

}
