package challenge.dao;

import challenge.model.Team;
import challenge.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TeamDao extends CrudRepository<Team, Long> {

    public User findByName(String username);

    public List<Team> findAll();

    public Team findOne(Long teamId);

    public Team save(Team team);

    public void delete(Team team);

}
