package challenge.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TeamDTO_test {

    @Test
    public void test() {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(111L);
        teamDTO.setName("name");

        assertEquals(new Long(111), teamDTO.getId());
        assertEquals("name", teamDTO.getName());
        assertNotNull(teamDTO.toString());
    }

}
