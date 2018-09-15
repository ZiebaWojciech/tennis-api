package pl.coderslab.tennisApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennisApi.entity.AtpRanking;

public interface AtpRankingRepository extends JpaRepository<AtpRanking, Integer> {
}
