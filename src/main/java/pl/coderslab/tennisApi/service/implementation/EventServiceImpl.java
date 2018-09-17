package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.*;
import pl.coderslab.tennisApi.repository.EventRepository;
import pl.coderslab.tennisApi.service.EventService;
import pl.coderslab.tennisApi.service.TennisSetService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private TennisSetService tennisSetService;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setTennisSetService(TennisSetService tennisSetService) {
        this.tennisSetService = tennisSetService;
    }

    @Override
    public Event getOne(int id) {
        return eventRepository.getOne(id);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void startEvent(Event event) {
        event.setStatus(EventStatus.IN_PROGRESS);
        Result result = new Result();
        event.setResult(result);
        tennisSetService.newSetInMatch(event);
        eventRepository.save(event);
    }

    @Override
    public void endOfMatch(Event event, Player winnerOfSet) {
        Player looser = event.getPlayerOne().equals(winnerOfSet) ? event.getPlayerTwo() : event.getPlayerOne();
        event.getResult().setLooser(looser);
        event.getResult().setWinner(winnerOfSet);
        event.setStatus(EventStatus.COMPLETED);
        save(event);
    }

}
