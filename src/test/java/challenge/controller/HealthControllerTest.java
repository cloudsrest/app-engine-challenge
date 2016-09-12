package challenge.controller;

import challenge.dao.UserDao;
import challenge.dto.Health;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest()
public class HealthControllerTest {

    @Autowired
    HealthController healthController;

    @Test
    public void testHealth_positive() {
        Health health = healthController.health();
        assertEquals(health.getStatus(), HealthController.STATUS);
    }

    @Test()
    public void testHealth_negative() {
        UserDao mock = Mockito.mock(UserDao.class);
        String message = "Some really bad thing happen";
        Mockito.when(mock.findAll()).thenThrow(new RuntimeException(message));
        healthController.setUserDao(mock);

        Health health = healthController.health();
        assertNotNull(health.getStatus().concat(message));
    }

}
