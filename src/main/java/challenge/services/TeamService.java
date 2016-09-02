package challenge.services;

import challenge.model.Team;

import java.util.List;

public interface TeamService {

    public List<Team> findAll();
    public Team createTeam(Team team);

}
