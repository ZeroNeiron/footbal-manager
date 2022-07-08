package footballmanager.repository;

import java.util.List;
import footballmanager.model.Player;
import footballmanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByTeam(Team team);
}
