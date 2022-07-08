package footballmanager.service;

import java.util.List;
import footballmanager.model.Player;
import footballmanager.model.Team;

public interface PlayerService {
    Player savePlayer(Player player);

    Player getPlayerById(Long id);

    void deletePlayerById(Long id);

    List<Player> getAllPlayers();

    List<Player> getAllPlayersByTeam(Team team);
}
