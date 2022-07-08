package footballmanager.mapper.request;

import footballmanager.dto.request.PlayerRequestDto;
import footballmanager.mapper.RequestDtoMapper;
import footballmanager.model.Player;
import footballmanager.service.TeamService;
import org.springframework.stereotype.Component;

@Component
public class PlayerRequestMapper implements RequestDtoMapper<PlayerRequestDto, Player> {
    private final TeamService teamService;

    public PlayerRequestMapper(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public Player mapToModel(PlayerRequestDto playerRequestDto) {
        Player player = new Player();
        player.setName(playerRequestDto.getFirstName());
        player.setSurname(playerRequestDto.getLastName());
        player.setAge(playerRequestDto.getAge());
        player.setMonthsOfExperience(playerRequestDto.getMonthsOfExperience());
        player.setTeam(teamService.getTeamById(playerRequestDto.getTeamId()));
        return player;
    }
}