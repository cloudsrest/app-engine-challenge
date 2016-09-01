package challenge.services.impl;

import challenge.dto.Recognition;
import challenge.dto.User;
import challenge.services.RecognitionService;
import challenge.util.MockDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recognitionService")
public class RecognitionServiceMock implements RecognitionService {

    private MockDB mockDB = MockDB.getInstance();

    @Override
    public List<Recognition> getRecognitions(User user) {
        return mockDB.getRecognitions();
    }

    @Override
    public void createRecognition(User user, Recognition recognition) {
        mockDB.addRecognition(recognition);
    }

    @Override
    public void deleteAllRecognitions() {
        mockDB.deleteAllRecognitions();
    }

}
