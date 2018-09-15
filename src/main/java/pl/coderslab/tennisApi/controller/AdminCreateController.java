package pl.coderslab.tennisApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.tennisApi.entity.Country;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.EventStatus;
import pl.coderslab.tennisApi.service.EventService;
import pl.coderslab.tennisApi.service.PlayerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/create")
public class AdminCreateController {
    private final PlayerService playerService;
    private final EventService eventService;

    @Autowired
    public AdminCreateController(PlayerService playerService, EventService eventService) {
        this.playerService = playerService;
        this.eventService = eventService;
    }

    @ModelAttribute
    public void setModelAttributes(Model model){
        model.addAttribute("countries", Country.values());
        model.addAttribute("statuses", EventStatus.values());
        model.addAttribute("players", playerService.getAll());
    }
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String adminPage(){
        return "admin_page";
    }

    @RequestMapping(path = "/event", method = RequestMethod.GET)
    public String createEventGet(Model model) {
        model.addAttribute("event", new Event());
        return "create_event";
    }

    @RequestMapping(path = "/event", method = RequestMethod.POST)
    public String createEventPost(@Valid Event event, BindingResult result) {
        if(result.hasErrors()){
            return "create_event";
        }
        eventService.save(event);
        return "redirect:/admin";
    }
}
