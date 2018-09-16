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
    private ResultService resultService;

    @Autowired
    public TennisGameServiceImpl(TennisGameRepository tennisGameRepository) {
        this.tennisGameRepository = tennisGameRepository;
    }

    @Autowired
    public void setTennisSetService(TennisSetService tennisSetService) {
        this.tennisSetService = tennisSetService;
    }
    @Autowired
    public void setResultService(ResultService resultService) {
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
    public void playerWinsPoint(Result result, Player winnerOfPoint) {
        TennisGame currentTennisGame = resultService.getCurrentGame(result);
        TennisSet currentTennisSet = resultService.getCurrentSet(result);
//        addPointToPlayer(currentTennisGame, winnerOfPoint);//TODO
        if (winnerOfPoint.equals(result.getEvent().getPlayerOne())) {
            currentTennisGame.setPlayerOnePoints(currentTennisGame.getPlayerOnePoints() + 1);
        } else {
            currentTennisGame.setPlayerTwoPoints(currentTennisGame.getPlayerTwoPoints() + 1);
        }

        if (endOfGame(result)) {
            currentTennisGame.setTennisGameWinner(winnerOfPoint);
            currentTennisGame.setInPlay(false);
            tennisSetService.playerWinsGame(result, winnerOfPoint);

        }
        save(currentTennisGame);
    }

//    private void addPointToPlayer(TennisGame currentGame, Player winnerOfPoint) {
//    }

    @Override
    public boolean endOfGame(Result result) {
        TennisGame currentTennisGame = resultService.getCurrentGame(result);
        return (Math.abs(currentTennisGame.getPlayerOnePoints() - currentTennisGame.getPlayerTwoPoints()) >= 2 && (currentTennisGame.getPlayerOnePoints() >= 4 || currentTennisGame.getPlayerTwoPoints() >=4));
    }

    @Override
    public void newGameInCurrentSet(Result result) {
        TennisGame game = new TennisGame();
        game.setInPlay(true);
        TennisSet currentTennisSet = resultService.getCurrentSet(result);
        currentTennisSet.addGame(game);
        tennisSetService.save(currentTennisSet);
    }



}
