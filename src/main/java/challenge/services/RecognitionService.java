package challenge.services;

import challenge.dto.Recognition;
import challenge.dto.User;

import java.util.List;

public interface RecognitionService {

    public List<Recognition> getRecognitions(User user);
    public void createRecognition(User user, Recognition recognition);
    public void deleteAllRecognitions();

}
