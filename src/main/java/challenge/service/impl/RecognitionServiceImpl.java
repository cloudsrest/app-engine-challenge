package challenge.service.impl;

import challenge.dao.RecognitionDao;
import challenge.dao.UserDao;
import challenge.dto.RecognitionDTO;
import challenge.dto.RecognitionTypeEnum;
import challenge.model.Recognition;
import challenge.model.User;
import challenge.service.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("recognitionService")
public class RecognitionServiceImpl implements RecognitionService {

    @Autowired
    RecognitionDao recognitionDao;

    @Autowired
    UserDao userDao;

    @Override
    public List<Recognition> getAllRecognitions(User user) {
        return recognitionDao.findAll();
    }

    @Override
    public List<Recognition> getMyRecognitions(User user) {
        return recognitionDao.findByToUser(user);
    }

    @Override
    public Recognition createRecognition(User user, RecognitionDTO recognitionDTO) {
        User fromUser = userDao.findOne(recognitionDTO.getFromUserId());
        User toUser = userDao.findOne(recognitionDTO.getToUserId());
        Recognition recognition = new Recognition();
        recognition.setFromUser(fromUser);
        recognition.setToUser(toUser);
        recognition.setComment(recognitionDTO.getComment());
        if (recognitionDTO.getType() != null) {
            recognition.setRecognitionType(RecognitionTypeEnum.valueOf(recognitionDTO.getType()));
        }
        recognition.setTimestamp(new Date());
        return recognitionDao.save(recognition);
    }

    public long getTotalRecognitions() {
        return recognitionDao.totalRecognitions();
    }

    public List<challenge.dao.RecognitionSummary> getTopRecognitionReceivers() {
        return recognitionDao.topRecognitionReceivers();
    }
}
