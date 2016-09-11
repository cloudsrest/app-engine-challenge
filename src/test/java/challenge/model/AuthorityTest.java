package challenge.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthorityTest {


    @Test
    public void test() {
        Authority authority1 = new Authority();
        authority1.setName("name");

        Authority authority2 = new Authority();
        authority2.setName("name");

        assertEquals("name", authority1.getName());
        assertEquals(authority1, authority2);

        assertNotNull(authority1.toString());
        assertNotNull(authority1);
        assertNotNull(authority2);
    }

}
