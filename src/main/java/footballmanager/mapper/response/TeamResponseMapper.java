package footballmanager.mapper.response;

import footballmanager.dto.response.TeamResponseDto;
import footballmanager.mapper.ResponseDtoMapper;
import footballmanager.model.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamResponseMapper implements ResponseDtoMapper<TeamResponseDto, Team> {
    @Override
    public TeamResponseDto mapToDto(Team team) {
        TeamResponseDto teamResponseDto = new TeamResponseDto();
        teamResponseDto.setId(team.getId());
        teamResponseDto.setTeamName(team.getName());
        teamResponseDto.setCommissionTransferPercent(team.getCommissionPercent());
        teamResponseDto.setBalance(team.getBalance());
        return teamResponseDto;
    }
}