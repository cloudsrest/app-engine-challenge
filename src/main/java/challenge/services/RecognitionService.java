package challenge.services;

import challenge.dto.RecognitionDTO;
import challenge.model.Recognition;
import challenge.model.User;

import java.util.List;

public interface RecognitionService {

    public List<Recognition> getAllRecognitions(User user);
    public List<Recognition> getMyRecognitions(User user);
    public void createRecognition(User user, RecognitionDTO recognition);

}
