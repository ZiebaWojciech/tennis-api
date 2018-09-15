package pl.coderslab.tennisApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennisApi.entity.TennisGame;
import pl.coderslab.tennisApi.entity.TennisSet;

public interface TennisGameRepository extends JpaRepository<TennisGame, Integer> {
}
