package pl.coderslab.tennisApi.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import pl.coderslab.tennisApi.dto.PlayerDTO;
import pl.coderslab.tennisApi.entity.AtpRankingPosition;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.service.AtpRankingPositionService;
import pl.coderslab.tennisApi.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private PlayerService playerService;
    private AtpRankingPositionService atpRankingPositionService;

    public PlayerController(PlayerService playerService, AtpRankingPositionService atpRankingPositionService) {
        this.playerService = playerService;
        this.atpRankingPositionService = atpRankingPositionService;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Player> getAllPlayers(){
        return playerService.getAll();
    }
//    @RequestMapping(value = "/dto/{playerId}", method = RequestMethod.GET)
//    public PlayerDTO getPlayerDTO(@PathVariable int playerId){
//        Player player = playerService.getOne(playerId);
//        List<AtpRankingPosition> playersAtpRanking = atpRankingPositionService.getAllByPlayerId(playerId);
//        return new PlayerDTO(player, playersAtpRanking);
//    }

    @RequestMapping(value = "/{playerId}", method = RequestMethod.GET)
    public Player getOnePlayer(@PathVariable int playerId){
        return playerService.getOne(playerId);
    }
}
