package challenge.services.impl;

import challenge.daos.TeamDao;
import challenge.model.Team;
import challenge.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teamService")
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    public List<Team> findAll() {
        return teamDao.findAll();
    }

    public Team createTeam(Team team) {
        return teamDao.save(team);
    }

}
