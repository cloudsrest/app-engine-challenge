package challenge.daos;

import challenge.model.Team;
import challenge.model.User;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserDaoTest extends BaseDaoTest {


    @Test
    public void testSaveUser() {
        assertNotNull(userDao);
        String user = "user1";

        User staged = getUser(user);
        User saved = userDao.save(staged);
        assertNotNull(saved);
        assertEquals(staged.getFirstName(), saved.getFirstName());

        long savedId = saved.getId();
        assertNotNull(savedId);

        Team team = saved.getTeam();
        assertNotNull(team);
        assertEquals(TEST_TEAM_NAME, team.getName());

        cleanup();

        User one = userDao.findOne(savedId);
        assertNull(one);
    }




}
