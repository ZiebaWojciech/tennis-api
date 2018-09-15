package pl.coderslab.tennisApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennisApi.entity.TennisSet;

@Repository
public interface TennisSetRepository extends JpaRepository<TennisSet, Integer> {
}
