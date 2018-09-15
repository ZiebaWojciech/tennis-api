package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.AtpRankingPosition;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.repository.AtpRankingPositionRepository;
import pl.coderslab.tennisApi.repository.EventRepository;
import pl.coderslab.tennisApi.service.AtpRankingPositionService;
import pl.coderslab.tennisApi.service.EventService;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    final
    EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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
}
