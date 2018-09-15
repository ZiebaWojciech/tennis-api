package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.Result;
import pl.coderslab.tennisApi.entity.TennisGame;
import pl.coderslab.tennisApi.entity.TennisSet;
import pl.coderslab.tennisApi.repository.ResultRepository;
import pl.coderslab.tennisApi.repository.TennisGameRepository;
import pl.coderslab.tennisApi.service.ResultService;
import pl.coderslab.tennisApi.service.TennisGameService;
import pl.coderslab.tennisApi.service.TennisSetService;

import java.util.List;

@Service
public class TennisGameServiceImpl implements TennisGameService {
    final TennisGameRepository tennisGameRepository;
    final TennisSetService tennisSetService;
    final ResultService resultService;

    @Autowired
    public TennisGameServiceImpl(TennisGameRepository tennisGameRepository, TennisSetService tennisSetService, ResultService resultService) {
        this.tennisGameRepository = tennisGameRepository;
        this.tennisSetService = tennisSetService;
        this.resultService = resultService;
    }

    @Override
    public TennisGame getOne(int id) {
        return tennisGameRepository.getOne(id);
    }

    @Override
    public List<TennisGame> getAll() {
        return tennisGameRepository.findAll();
    }

    @Override
    public TennisGame save(TennisGame tennisGame) {
        return tennisGameRepository.save(tennisGame);
    }


    @Override
    public void playerOneWinsPoint(Result result) {
        TennisGame currentGame = resultService.getCurrentGame(result);
        currentGame.setPlayerOnePoints(currentGame.getPlayerOnePoints() + 1);
        if (currentGame.getPlayerOnePoints() - currentGame.getPlayerTwoPoints() == 2 && currentGame.getPlayerOnePoints() >= 4) {
            currentGame.setTennisGameWinner(result.getEvent().getPlayerOne());
            tennisSetService.playerOneWinsGame(result);
        }
    }

    @Override
    public void playerTwoWinsPoint(Result result) {
        TennisGame currentGame = resultService.getCurrentGame(result);
        currentGame.setPlayerTwoPoints(currentGame.getPlayerTwoPoints() + 1);
        if (currentGame.getPlayerTwoPoints() - currentGame.getPlayerOnePoints() == 2 && currentGame.getPlayerTwoPoints() >= 4) {
            currentGame.setTennisGameWinner(result.getEvent().getPlayerTwo());
            tennisSetService.playerTwoWinsGame(result);
        }
    }


}
