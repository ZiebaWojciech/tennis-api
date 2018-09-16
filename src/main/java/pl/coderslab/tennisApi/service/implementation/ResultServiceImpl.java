package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.*;
import pl.coderslab.tennisApi.repository.ResultRepository;
import pl.coderslab.tennisApi.service.ResultService;
import pl.coderslab.tennisApi.service.TennisGameService;
import pl.coderslab.tennisApi.service.TennisSetService;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;

    private final TennisGameService tennisGameService;
    private final TennisSetService tennisSetService;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, TennisGameService tennisGameService, TennisSetService tennisSetService) {
        this.resultRepository = resultRepository;
        this.tennisGameService = tennisGameService;
        this.tennisSetService = tennisSetService;
    }

    @Override
    public Result getOne(int id) {
        return resultRepository.getOne(id);
    }

    @Override
    public List<Result> getAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result save(Result result) {
        return resultRepository.save(result);
    }


    @Override
    public Result startEvent(Event event) {
        Result result = new Result();
        result.setEvent(event);
        TennisSet tennisSet = new TennisSet();
        tennisSet.setInPlay(true);
        tennisGameService.newGameInCurrentSet(result);
        tennisSetService.newSetInMatch(result);
        return resultRepository.save(result);
    }

    @Override
    public TennisSet getCurrentSet(Result result) {
        return result.getSets().stream()
                .filter(TennisSet::isInPlay)
                .findAny()
                .orElse(null);
    }

    @Override
    public TennisGame getCurrentGame(Result result) {
        return getCurrentSet(result).getGames().stream()
                .filter(TennisGame::isInPlay)
                .findAny()
                .orElse(null);
    }

//    @Override
//    public void playerOneWinsPoint(Result result) {
//        TennisGame currentGame = getCurrentGame(result);
//
//        tennisSetService.playerOneWinsGame(result);
//    }


//    @Override
//    public void playerOneWinsSet(Result result) {
//        Player playerOne = result.getEvent().getPlayerOne();
//        getCurrentSet(result).setTennisSetWinner(playerOne);
//        long tennisSetWonByPlayerOne = result.getSets().stream()
//                .filter(s -> s.getTennisSetWinner().equals(playerOne))
//                .count();
//        if (tennisSetWonByPlayerOne == 3) {
//            playerOneWinsMatch(result);
//        }
//    }
//
//    @Override
//    public void playerTwoWinsSet(Result result) {
//        Player playerTwo = result.getEvent().getPlayerTwo();
//        getCurrentSet(result).setTennisSetWinner(playerTwo);
//        long tennisSetWonByPlayerOne = result.getSets().stream()
//                .filter(s -> s.getTennisSetWinner().equals(playerTwo))
//                .count();
//        if (tennisSetWonByPlayerOne == 3) {
//            playerTwoWinsMatch(result);
//        }
//    }

//    @Override
//    public void playerTwoWinsMatch(Result result) {
//        result.setWinner(result.getEvent().getPlayerOne());
//        result.setLooser(result.getEvent().getPlayerTwo());
//    }

    @Override
    public void playerWinsSet(Result result, Player winnerOfSet) {
        if (endOfMatch(result, winnerOfSet)) {
            Player looser = result.getEvent().getPlayerOne().equals(winnerOfSet) ? result.getEvent().getPlayerTwo() : result.getEvent().getPlayerOne();
            result.setLooser(looser);
            result.setWinner(winnerOfSet);
            save(result);
        } else {
            tennisSetService.newSetInMatch(result);
        }
    }

    @Override
    public boolean endOfMatch(Result result, Player winnerOfLastSet) {
        long setsWonByPlayerOne = result.getSets().stream()
                .filter(s -> s.getTennisSetWinner().equals(winnerOfLastSet))
                .count();
        return (setsWonByPlayerOne == 3);
    }

//    @Override
//    public void playerOneWinsMatch(Result result) {
//        result.setWinner(result.getEvent().getPlayerTwo());
//        result.setLooser(result.getEvent().getPlayerOne());
//    }
    @Override
    public void playerWinsPointInMatch(Result result, TennisSet currentSet, TennisGame currentGame, Player winnerOfPoint) {
        tennisGameService.playerWinsPoint(currentSet, currentGame, winnerOfPoint);

    }
}
