package pl.coderslab.tennisApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennisApi.entity.Results;
import pl.coderslab.tennisApi.entity.TennisSet;

public interface TennisSetRepository extends JpaRepository<TennisSet, Integer> {
}
