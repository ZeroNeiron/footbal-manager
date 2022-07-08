package footballmanager.mapper.response;

import footballmanager.dto.response.PlayerResponseDto;
import footballmanager.mapper.ResponseDtoMapper;
import footballmanager.model.Player;
import footballmanager.service.TeamService;
import org.springframework.stereotype.Component;

@Component
public class PlayerResponseMapper implements ResponseDtoMapper<PlayerResponseDto, Player> {
    private final TeamService teamService;

    public PlayerResponseMapper(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public PlayerResponseDto mapToDto(Player player) {
        PlayerResponseDto playerResponseDto = new PlayerResponseDto();
        playerResponseDto.setId(player.getId());
        playerResponseDto.setFirstName(player.getName());
        playerResponseDto.setLastName(player.getSurname());
        playerResponseDto.setAge(player.getAge());
        playerResponseDto.setMonthsOfExperience(player.getMonthsOfExperience());
        playerResponseDto.setTeamId(player.getTeam().getId());
        return playerResponseDto;
    }
}