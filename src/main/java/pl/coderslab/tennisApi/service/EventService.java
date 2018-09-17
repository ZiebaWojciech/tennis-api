package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.AtpRankingPosition;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.Result;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    Event getOne(int id);
    List<Event> getAll();
    Event save(Event event);

    void startEvent(Event event);
    void endOfMatch(Event event, Player winnerOfLastSet);

}
