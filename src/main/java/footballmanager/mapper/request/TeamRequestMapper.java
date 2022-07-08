package footballmanager.mapper.request;

import footballmanager.dto.request.TeamRequestDto;
import footballmanager.mapper.RequestDtoMapper;
import footballmanager.model.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamRequestMapper implements RequestDtoMapper<TeamRequestDto, Team> {
    @Override
    public Team mapToModel(TeamRequestDto teamRequestDto) {
        Team team = new Team();
        team.setName(teamRequestDto.getTeamName());
        team.setCommissionPercent(teamRequestDto.getCommissionPercent());
        team.setBalance(teamRequestDto.getBalance());
        return team;
    }
}