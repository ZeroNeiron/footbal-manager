package footballmanager.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import footballmanager.dto.request.PlayerRequestDto;
import footballmanager.dto.response.PlayerResponseDto;
import footballmanager.mapper.request.PlayerRequestMapper;
import footballmanager.mapper.response.PlayerResponseMapper;
import footballmanager.model.Player;
import footballmanager.service.PlayerService;
import footballmanager.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    private final PlayerResponseMapper playerResponseMapper;
    private final PlayerRequestMapper playerRequestMapper;
    private final TeamService teamService;

    public PlayerController(PlayerService playerService, PlayerResponseMapper playerResponseMapper,
                            PlayerRequestMapper playerRequestMapper, TeamService teamService) {
        this.playerService = playerService;
        this.playerResponseMapper = playerResponseMapper;
        this.playerRequestMapper = playerRequestMapper;
        this.teamService = teamService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerResponseDto createPlayer(@Valid @RequestBody PlayerRequestDto playerRequestDto) {
        Player player = playerService.savePlayer(playerRequestMapper.mapToModel(playerRequestDto));
        return playerResponseMapper.mapToDto(player);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerResponseDto getById(@PathVariable Long id) {
        return playerResponseMapper.mapToDto(playerService.getPlayerById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerResponseDto> getAllPlayers() {
        return playerService.getAllPlayers().stream()
                .map(playerResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/team/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerResponseDto> getAllPlayersByTeam(@PathVariable Long id) {
        return playerService.getAllPlayersByTeam(teamService.getTeamById(id)).stream()
                .map(playerResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerResponseDto updatePlayer(@PathVariable Long id,
                                          @Valid @RequestBody PlayerRequestDto playerRequestDto) {
        Player player = playerRequestMapper.mapToModel(playerRequestDto);
        player.setId(id);
        return playerResponseMapper.mapToDto(playerService.savePlayer(player));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return "Player with id " + id + " was deleted";
    }
}