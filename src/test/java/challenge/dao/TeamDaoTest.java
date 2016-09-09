package challenge.dao;

import challenge.BaseTest;
import challenge.model.Team;
import challenge.model.User;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TeamDaoTest extends BaseTest {

    @Autowired
    TeamDao teamDao;

    public void testFindByName() {
        Team fetched = teamDao.findByName(getTeam().getName());
        assertEquals(getTeam().getName(), fetched.getName());
    }

}
