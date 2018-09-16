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


    public void playerWinsPoint(TennisSet tennisSet, TennisGame currentGame, Player winnerOfPoint) {
        currentGame.setPlayerOnePoints(currentGame.getPlayerOnePoints() + 1);
        if(endOfGame(currentGame)){
            currentGame.setTennisGameWinner(winnerOfPoint);
            tennisSetService.playerWinsGame(tennisSet, winnerOfPoint);

        }
        save(currentGame);
    }
    @Override
    public boolean endOfGame(TennisGame currentGame) {
        return (currentGame.getPlayerOnePoints() - currentGame.getPlayerTwoPoints() == 2 && currentGame.getPlayerOnePoints() >= 4);
    }

//    @Override
//    public void playerTwoWinsPoint(Result result) {
//        TennisGame currentGame = resultService.getCurrentGame(result);
//        currentGame.setPlayerTwoPoints(currentGame.getPlayerTwoPoints() + 1);
//        if (currentGame.getPlayerTwoPoints() - currentGame.getPlayerOnePoints() == 2 && currentGame.getPlayerTwoPoints() >= 4) {
//            currentGame.setTennisGameWinner(result.getEvent().getPlayerTwo());
//            tennisSetService.playerTwoWinsGame(result);
//        }
//    }

    @Override
    public void newGameInCurrentSet(TennisSet tennisSet) {
        TennisGame game = new TennisGame();
        tennisSet.addGame(game);
        tennisSetService.save(tennisSet);
    }



}
