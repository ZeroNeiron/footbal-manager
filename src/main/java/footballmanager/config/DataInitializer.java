package footballmanager.config;

import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import footballmanager.model.Player;
import footballmanager.model.Team;
import footballmanager.service.PlayerService;
import footballmanager.service.TeamService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final PlayerService playerService;
    private final TeamService teamService;

    public DataInitializer(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @PostConstruct
    public void inject() {
        Team barcelona = new Team();
        barcelona.setName("Barcelona");
        barcelona.setCommissionPercent(5.0);
        barcelona.setBalance(BigDecimal.valueOf(50000000));
        teamService.saveTeam(barcelona);

        Team arsenal = new Team();
        arsenal.setName("Arsenal");
        arsenal.setCommissionPercent(3.0);
        arsenal.setBalance(BigDecimal.valueOf(1200000));
        teamService.saveTeam(arsenal);

        Team realMadrid = new Team();
        realMadrid.setName("Real Madrid");
        realMadrid.setCommissionPercent(7.0);
        realMadrid.setBalance(BigDecimal.valueOf(1800000));
        teamService.saveTeam(realMadrid);

        Team villarreal = new Team();
        villarreal.setName("Villarreal");
        villarreal.setCommissionPercent(8.0);
        villarreal.setBalance(BigDecimal.valueOf(2000000));
        teamService.saveTeam(villarreal);

        Player lionelMessi = new Player();
        lionelMessi.setName("Lionel");
        lionelMessi.setSurname("Messi");
        lionelMessi.setAge(32);
        lionelMessi.setMonthsOfExperience(180);
        lionelMessi.setTeam(realMadrid);
        playerService.savePlayer(lionelMessi);

        Player cristianoRonaldo = new Player();
        cristianoRonaldo.setName("Cristiano");
        cristianoRonaldo.setSurname("Ronaldo");
        cristianoRonaldo.setAge(30);
        cristianoRonaldo.setMonthsOfExperience(103);
        cristianoRonaldo.setTeam(villarreal);
        playerService.savePlayer(cristianoRonaldo);

        Player robertLewandowski = new Player();
        robertLewandowski.setName("Robert");
        robertLewandowski.setSurname("Lewandowski");
        robertLewandowski.setAge(24);
        robertLewandowski.setMonthsOfExperience(29);
        robertLewandowski.setTeam(villarreal);
        playerService.savePlayer(robertLewandowski);
    }
}