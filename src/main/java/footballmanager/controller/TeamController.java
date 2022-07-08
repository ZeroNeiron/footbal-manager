package footballmanager.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import footballmanager.dto.request.TeamRequestDto;
import footballmanager.dto.response.TeamResponseDto;
import footballmanager.mapper.request.TeamRequestMapper;
import footballmanager.mapper.response.TeamResponseMapper;
import footballmanager.model.Team;
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
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    private final TeamRequestMapper teamRequestMapper;

    private final TeamResponseMapper teamResponseMapper;

    public TeamController(TeamService teamService,
                          TeamRequestMapper teamRequestMapper,
                          TeamResponseMapper teamResponseMapper) {
        this.teamService = teamService;
        this.teamRequestMapper = teamRequestMapper;
        this.teamResponseMapper = teamResponseMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDto createTeam(@Valid @RequestBody TeamRequestDto teamRequestDto) {
        Team team = teamService.saveTeam(teamRequestMapper.mapToModel(teamRequestDto));
        return teamResponseMapper.mapToDto(team);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamResponseDto getById(@PathVariable Long id) {
        return teamResponseMapper.mapToDto(teamService.getTeamById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeamResponseDto> getAllTeams() {
        return teamService.getAllTeams()
                .stream()
                .map(teamResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamResponseDto updateTeam(@PathVariable Long id,
                                      @Valid @RequestBody TeamRequestDto teamRequestDto) {
        Team team = teamRequestMapper.mapToModel(teamRequestDto);
        team.setId(id);
        return teamResponseMapper.mapToDto(teamService.saveTeam(team));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTeamById(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return "Team by id " + id + " is deleted";
    }
}