package pl.coderslab.tennisApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.tennisApi.entity.*;
import pl.coderslab.tennisApi.service.EventService;
import pl.coderslab.tennisApi.service.PlayerService;
import pl.coderslab.tennisApi.service.ResultService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/event")
public class AdminEventController {
    private final PlayerService playerService;
    private final EventService eventService;
    private final ResultService resultService;

    @Autowired
    public AdminEventController(PlayerService playerService, EventService eventService, ResultService resultService) {
        this.playerService = playerService;
        this.eventService = eventService;
        this.resultService = resultService;
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
        return "redirect:/admin/event/"+eventId+"/running";
    }

    @RequestMapping(path = "/{eventId}/running", method = RequestMethod.GET)
    public String runEvent(@PathVariable int eventId, Model model) {
        Event event = eventService.getOne(eventId);
        Result result = resultService.getOneByEvent(event);
        TennisGame currentGame = resultService.getCurrentGame(result);
        model.addAttribute("event", event);
        model.addAttribute("result", result);
        model.addAttribute("currentGame", currentGame);
        return "run_event";
    }

    @RequestMapping(path = "/{eventId}/add-point", method = RequestMethod.POST    )
    public String addPoint(@PathVariable int eventId, @RequestParam("playerId") int winnerOfPointId, Model model) {
        Event event = eventService.getOne(eventId);
        Result result = resultService.getOneByEvent(event);
        resultService.playerWinsPointInMatch(result, winnerOfPointId);
        return "redirect:/admin/event/"+eventId+"/running";
    }
}