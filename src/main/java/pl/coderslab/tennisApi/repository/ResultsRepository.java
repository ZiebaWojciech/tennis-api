package pl.coderslab.tennisApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.Results;

public interface ResultsRepository extends JpaRepository<Results, Integer> {
}
