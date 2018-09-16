package pl.coderslab.tennisApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
//    Result getByEvent(Event event);
}
