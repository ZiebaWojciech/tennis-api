package pl.coderslab.feedingtennisapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.feedingtennisapi.entity.AtpRanking;

public interface AtpRankingRepository extends JpaRepository<AtpRanking, Integer> {
}
