package challenge.dto;

import org.junit.Assert;
import org.junit.Test;

public class RecognitionTypeEnumTest {

    @Test
    public void test() {
        RecognitionTypeEnum e = RecognitionTypeEnum.CREATIVITY;
        e = RecognitionTypeEnum.HARD_WORK;
        e = RecognitionTypeEnum.INNOVATION;
        e = RecognitionTypeEnum.TEAM_WORK;

        RecognitionTypeEnum[] values = RecognitionTypeEnum.values();
        for (RecognitionTypeEnum value : values) {
            e = RecognitionTypeEnum.valueOf(value.toString());
            Assert.assertNotNull(e);
        }

    }

}
