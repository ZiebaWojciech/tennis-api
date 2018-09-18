package pl.coderslab.tennisApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.tennisApi.entity.Event;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String adminPage(){
        return "admin_page";
    }


}
