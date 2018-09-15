package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.*;
import pl.coderslab.tennisApi.repository.EventRepository;
import pl.coderslab.tennisApi.repository.ResultRepository;
import pl.coderslab.tennisApi.repository.TennisGameRepository;
import pl.coderslab.tennisApi.repository.TennisSetRepository;
import pl.coderslab.tennisApi.service.EventService;
import pl.coderslab.tennisApi.service.ResultService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ResultService resultService;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ResultService resultService) {
        this.eventRepository = eventRepository;
        this.resultService = resultService;

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
    public Result startEvent(Event event) {
        event.setStatus(EventStatus.IN_PROGRESS);
        eventRepository.save(event);
        return resultService.startEvent(event);
    }
}
