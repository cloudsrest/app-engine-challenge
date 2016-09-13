package challenge.dto;

import org.junit.Assert;
import org.junit.Test;

public class RecognitionTypeEnumTest {

    @Test
    public void test() {
        RecognitionTypeEnum e = RecognitionTypeEnum.TEAMWORK;
        e = RecognitionTypeEnum.IMPROVEMENT;
        e = RecognitionTypeEnum.DELIVERY;
        e = RecognitionTypeEnum.EXPERIMENT;

        RecognitionTypeEnum[] values = RecognitionTypeEnum.values();
        for (RecognitionTypeEnum value : values) {
            e = RecognitionTypeEnum.valueOf(value.toString());
            Assert.assertNotNull(e);
        }

    }

}
