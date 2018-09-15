package pl.coderslab.tennisApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.tennisApi.entity.Country;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.EventStatus;
import pl.coderslab.tennisApi.entity.Result;
import pl.coderslab.tennisApi.service.EventService;
import pl.coderslab.tennisApi.service.PlayerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/event")
public class AdminEventController {
    private final PlayerService playerService;
    private final EventService eventService;

    @Autowired
    public AdminEventController(PlayerService playerService, EventService eventService) {
        this.playerService = playerService;
        this.eventService = eventService;
    }

    @ModelAttribute
    public void setModelAttributes(Model model){
        model.addAttribute("countries", Country.values());
        model.addAttribute("statuses", EventStatus.values());
        model.addAttribute("players", playerService.getAll());
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createEventGet(Model model) {
        model.addAttribute("event", new Event());
        return "create_event";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createEventPost(@Valid Event event, BindingResult result) {
        if(result.hasErrors()){
            return "create_event";
        }
        eventService.save(event);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String allEvents(Model model) {
        model.addAttribute("events", eventService.getAll());
        return "all_event";
    }

    @RequestMapping(path = "/{eventId}/start", method = RequestMethod.GET)
    public String startEvent(@PathVariable int eventId, Model model) {
        Event event = eventService.getOne(eventId);
        Result result = eventService.startEvent(event);
        model.addAttribute("event", event);
        model.addAttribute("result", result);
        return "run_event";
    }
}
