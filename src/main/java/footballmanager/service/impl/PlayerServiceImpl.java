package footballmanager.service.impl;

import java.beans.Transient;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import footballmanager.model.Player;
import footballmanager.model.Team;
import footballmanager.repository.PlayerRepository;
import footballmanager.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't get player by id " + id));
    }

    @Transactional
    @Override
    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> getAllPlayersByTeam(Team team) {
        return playerRepository.findAllByTeam(team);
    }
}
