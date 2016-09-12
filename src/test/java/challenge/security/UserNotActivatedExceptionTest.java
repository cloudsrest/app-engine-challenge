package challenge.security;

import org.junit.Test;

public class UserNotActivatedExceptionTest {

    @Test(expected = UserNotActivatedException.class)
    public void testUserNotActivatedException() {
        throw new UserNotActivatedException("oh no!");
    }

    @Test(expected = UserNotActivatedException.class)
    public void testUserNotActivatedException_w_throwable() {
        String[] s = new String[2];
        try {
            String s1 = s[10];
        } catch (Exception e) {
            throw new UserNotActivatedException("oh no!", e);
        }
    }

}
