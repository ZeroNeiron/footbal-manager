package footballmanager.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import footballmanager.model.Team;
import footballmanager.repository.TeamRepository;
import footballmanager.service.TeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't get team by id " + id));
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }
}
