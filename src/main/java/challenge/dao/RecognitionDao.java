package challenge.dao;

import challenge.model.Recognition;
import challenge.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RecognitionDao  extends CrudRepository<Recognition, Long> {

    public Recognition save(Recognition recognition);

    public List<Recognition> findAll();

    public List<Recognition> findByToUser(User toUser);
}
