package challenge.dao;

import challenge.model.Team;
import challenge.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TeamDao extends CrudRepository<Team, Long> {

    public Team findByName(String name);
    public List<Team> findAll();

}
