package pl.coderslab.tennisApi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.tennisApi.entity.Country;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.repository.PlayerRepository;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Player players(){
        return new Player(1, "Mike", "Higgins", Country.GBR, Date.valueOf("2000-02-02"));
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }
}
