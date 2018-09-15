package pl.coderslab.tennisApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennisApi.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
