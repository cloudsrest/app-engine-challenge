package challenge.dao;

import challenge.model.Recognition;
import challenge.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public interface RecognitionDao extends CrudRepository<Recognition, Long> {

    public Recognition save(Recognition recognition);
    public List<Recognition> findAll();
    public List<Recognition> findByToUser(User toUser);
    public List<Recognition> findByFromUser(User toUser);

//    @Query("select challenge.model.Recognition from recognition where toUser.id = :toUser  and fromUser.id = :fromUser and timestamp > :yesterday  ")
    @Query("select r from challenge.model.Recognition r where toUser.id = :toUser  and fromUser.id = :fromUser and timestamp > :yesterday  ")
    public List<Recognition> findByFromToAndDate(@Param("toUser") Long toUser, @Param("fromUser") Long fromUser, @Param("yesterday") Date date);

    @Query("select count(*) from recognition")
    public long totalRecognitions();

    @Query("select new challenge.dao.RecognitionSummary(r.toUser.username, count(r) as summaryCount) from recognition r group by r.toUser.username order by summaryCount desc")
    public List<RecognitionSummary> topRecognitionReceivers();

    @Query("select new challenge.dao.RecognitionSummary(r.fromUser.username, count(r) as summaryCount) from recognition r group by r.fromUser.username order by summaryCount desc")
    public List<RecognitionSummary> topRecognitionSenders();

}
