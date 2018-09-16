package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.*;
import pl.coderslab.tennisApi.repository.ResultRepository;
import pl.coderslab.tennisApi.repository.TennisGameRepository;
import pl.coderslab.tennisApi.service.ResultService;
import pl.coderslab.tennisApi.service.TennisGameService;
import pl.coderslab.tennisApi.service.TennisSetService;

import java.util.List;

@Service
public class TennisGameServiceImpl implements TennisGameService {
    private final TennisGameRepository tennisGameRepository;
    private TennisSetService tennisSetService;

    @Autowired
    public TennisGameServiceImpl(TennisGameRepository tennisGameRepository) {
        this.tennisGameRepository = tennisGameRepository;
    }

    @Autowired
    public void setTennisSetService(TennisSetService tennisSetService) {
        this.tennisSetService = tennisSetService;
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
    public void playerWinsPoint(Result result, TennisSet currentTennisSet, TennisGame currentGame, Player winnerOfPoint) {
        if (winnerOfPoint.equals(result.getEvent().getPlayerOne())) {
            currentGame.setPlayerOnePoints(currentGame.getPlayerOnePoints() + 1);
        } else {
            currentGame.setPlayerTwoPoints(currentGame.getPlayerTwoPoints() + 1);
        }

        if (endOfGame(currentGame)) {
            currentGame.setTennisGameWinner(winnerOfPoint);
            currentGame.setInPlay(false);
            tennisSetService.playerWinsGame(currentTennisSet, winnerOfPoint);

        }
        save(currentGame);
    }

    @Override
    public boolean endOfGame(TennisGame currentGame) {
        return (Math.abs(currentGame.getPlayerOnePoints() - currentGame.getPlayerTwoPoints()) >= 2 && (currentGame.getPlayerOnePoints() >= 4 || currentGame.getPlayerTwoPoints() >=4));
    }

    @Override
    public void newGameInCurrentSet(TennisSet tennisSet) {
        TennisGame game = new TennisGame();
        game.setInPlay(true);
        tennisSet.addGame(game);
        tennisSetService.save(tennisSet);
    }


}
