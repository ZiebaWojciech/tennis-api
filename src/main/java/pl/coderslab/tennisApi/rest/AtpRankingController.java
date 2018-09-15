package pl.coderslab.tennisApi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.tennisApi.entity.AtpRankingPosition;
import pl.coderslab.tennisApi.repository.AtpRankingPositionRepository;

import java.util.List;

@RestController
@RequestMapping("/atp-rankings")
public class AtpRankingController {
    private final AtpRankingPositionRepository atpRankingPositionRepository;

    @Autowired
    public AtpRankingController(AtpRankingPositionRepository atpRankingPositionRepository) {
        this.atpRankingPositionRepository = atpRankingPositionRepository;
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<AtpRankingPosition> getAllPlayers() {
        return atpRankingPositionRepository.findAll();
    }

}
