package pl.coderslab.tennisApi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.service.EventService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Event> getAllEvents(){
        return eventService.getAll();
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public Event getOneEvent(@PathVariable int eventId){
        return  eventService.getOne(eventId);
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public Event getNewEvent(){
        return new Event();
    }
}
