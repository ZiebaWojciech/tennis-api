package pl.coderslab.feedingtennisapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.feedingtennisapi.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
