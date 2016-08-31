package challenge.util;

import challenge.dto.Recognition;

import java.util.ArrayList;
import java.util.List;

public class MockDB {
    private static MockDB ourInstance = new MockDB();

    private List<Recognition> recognitions = new ArrayList<>();

    public static MockDB getInstance() {
        return ourInstance;
    }

    private MockDB() {
    }

    public void addRecognition(Recognition recognition) {
        recognitions.add(recognition);
    }

    public List<Recognition> getRecognitions() {
        return recognitions;
    }
}
