package pl.coderslab.tennisApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String adminPage(){
        return "admin_page";
    }

    @RequestMapping(path = "/create-event", method = RequestMethod.GET)
    public String createEvent(Model model) {
        model.addAttribute("event", )
        return "create_event";
    }
}
