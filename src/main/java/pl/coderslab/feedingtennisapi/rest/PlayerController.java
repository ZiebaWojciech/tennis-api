package pl.coderslab.feedingtennisapi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.feedingtennisapi.entity.Country;
import pl.coderslab.feedingtennisapi.entity.Player;

import java.sql.Date;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Player test(){
        return new Player(1, "Mike", "Higgins", Country.GBR, Date.valueOf("2000-02-02"));

    }
}
