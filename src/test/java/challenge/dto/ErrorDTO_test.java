package challenge.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ErrorDTO_test {

    @Test
    public void test() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(1);
        errorDTO.setMessage("msg");
        errorDTO.setError("err");
        errorDTO.setPath("path");
        errorDTO.setTimestamp(111);

        assertEquals(1, errorDTO.getStatus());
        assertEquals("msg", errorDTO.getMessage());
        assertEquals("err", errorDTO.getError());
        assertEquals("path", errorDTO.getPath());
        assertEquals(111, errorDTO.getTimestamp());
        assertEquals("path", errorDTO.getPath());
        assertNotNull(errorDTO.toString());

    }

}
