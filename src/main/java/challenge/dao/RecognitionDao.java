package challenge.dao;

import challenge.model.Recognition;
import challenge.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RecognitionDao extends CrudRepository<Recognition, Long> {

    public Recognition save(Recognition recognition);
    public List<Recognition> findAll();
    public List<Recognition> findByToUser(User toUser);
    public List<Recognition> findByFromUser(User toUser);
    @Query("select count(*) from recognition")
    public long totalRecognitions();

    @Query("select new challenge.dao.RecognitionSummary(r.toUser.username, count(r) as summaryCount) from recognition r group by r.toUser.username order by summaryCount desc")
    public List<RecognitionSummary> topRecognitionReceivers();

}
