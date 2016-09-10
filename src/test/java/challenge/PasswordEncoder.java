package challenge;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncoder {


    @Test
    public void testEncodePassword() {

        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        String pass = encoder.encode("admin");

        System.out.println("pass = " + pass);
        Assert.assertNotNull(pass);
    }

}
