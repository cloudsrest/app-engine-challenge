package challenge;

import challenge.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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


    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(new UserDTO(1L, "userName", "firstName", "lasteName", false, 1L, "pass"));
        System.out.println("s = " + s);
    }

}
