package footballmanager.service;

import java.util.List;
import footballmanager.model.Team;

public interface TeamService {
    Team saveTeam(Team team);

    Team getTeamById(Long id);

    List<Team> getAllTeams();

    void deleteTeamById(Long id);
}
