package challenge.dao;

import challenge.BaseDaoTest;
import challenge.model.Team;
import challenge.model.User;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class UserDaoTest extends BaseDaoTest {

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

        cleanup();
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
        String usrName = "someUsr";
        User saved = userDao.save(getUserSaved(usrName));
        User fetched = userDao.findByUsername(TEST_USR_PREFIX + usrName);
        assertEquals(saved.getId(), fetched.getId());
    }


}
