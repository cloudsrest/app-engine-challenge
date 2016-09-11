package challenge.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDTO_test {

    public void test() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setAdmin(false);
        userDTO.setFirstName("first");
        userDTO.setLastName("last");
        userDTO.setTeam(1L);
        userDTO.setUsername("un");


        assertEquals(new Long(1), userDTO.getId());
        assertEquals(false, userDTO.isAdmin());
        assertEquals("first", userDTO.getFirstName());
        assertEquals("last", userDTO.getLastName());
        assertEquals(new Long(1), userDTO.getTeam());
        assertEquals("un", userDTO.getUsername());

        assertNotNull(userDTO.toString());
    }

}
