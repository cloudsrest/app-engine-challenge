package challenge.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RecognitionDTO_test {

    @Test
    public void test() {
        RecognitionDTO rec = new RecognitionDTO();
        rec.setComment("comment");
        rec.setFromUserId(111L);
        rec.setId(1L);
        rec.setTimestamp(111L);
        rec.setToUserId(222L);
        rec.setType("type");

        assertEquals("comment", rec.getComment());
        assertEquals(new Long(111), rec.getFromUserId());
        assertEquals(new Long(1), rec.getId());
        assertEquals(new Long(111), rec.getTimestamp());
        assertEquals(new Long(222), rec.getToUserId());
        assertEquals("type", rec.getType());

        assertNotNull(rec.toString());
    }

}
