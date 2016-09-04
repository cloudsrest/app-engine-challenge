package challenge.dao;

import challenge.Application;
import challenge.controller.RecognitionController;
import challenge.dao.RecognitionDao;
import challenge.dao.TeamDao;
import challenge.dao.UserDao;
import challenge.dto.RecognitionTypeEnum;
import challenge.model.Recognition;
import challenge.model.Team;
import challenge.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BaseDaoTest {

    public static final String TEST_TEAM_NAME = "team rocket";

    public User testUser = null;


    @Autowired
    public UserDao userDao;

    @Autowired
    public TeamDao teamDao;

    @Autowired
    public RecognitionDao recognitionDao;

    @Test
    public void stub() {
        assertTrue(true);
    }

    public User getUserSaved(String userId) {
        User save = userDao.save(getUser(userId));
        return save;
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
            testUser = getUserSaved("fromUser");
        }
        return testUser;
    }

}
