package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.AtpRankingPosition;
import pl.coderslab.tennisApi.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    Event getOne(int id);
    List<Event> getAll();
    Event save(Event event);
}
