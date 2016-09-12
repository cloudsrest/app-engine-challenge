package challenge.dao;

import challenge.BaseTest;
import challenge.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserDaoTest extends BaseTest {

    @Test
    public void testSaveUser() {
        assertNotNull(userDao);
        String user = "user1";

        User staged = getUser(user);
        User saved = userDao.save(staged);
        assertNotNull(saved);
        long savedId = saved.getId();
        assertNotNull(savedId);
        assertEquals(staged.getFirstName(), saved.getFirstName());
    }

    @Test
    public void findOne() {
        User saved = userDao.save(getUser("someUSer"));
        User one = userDao.findOne(saved.getId());
        assertNotNull(one);
        assertEquals(saved.getFirstName(), saved.getFirstName());
    }

    @Test
    public void testDelete() {
        User saved = userDao.save(getUser("someUSer"));
        User one = userDao.findOne(saved.getId());
        assertNotNull(one);
        assertEquals(saved.getFirstName(), saved.getFirstName());
        userDao.delete(saved);
        one = userDao.findOne(saved.getId());
        assertNull(one);
    }

    @Test
    public void testFindByUserName() {
        User saved = getUser("someUser");
        User fetched = userDao.findByUsername(saved.getUsername());
        assertEquals(saved.getId(), fetched.getId());
    }


}
