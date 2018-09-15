package pl.coderslab.tennisApi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.tennisApi.entity.AtpRanking;
import pl.coderslab.tennisApi.entity.Country;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.repository.AtpRankingRepository;
import pl.coderslab.tennisApi.repository.PlayerRepository;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/atp-rankings")
public class AtpRankingController {
    private final AtpRankingRepository atpRankingRepository;

    @Autowired
    public AtpRankingController(AtpRankingRepository atpRankingRepository) {
        this.atpRankingRepository = atpRankingRepository;
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<AtpRanking> getAllPlayers() {
        return atpRankingRepository.findAll();
    }

}
