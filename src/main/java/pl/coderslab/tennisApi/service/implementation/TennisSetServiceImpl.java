package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.Result;
import pl.coderslab.tennisApi.entity.TennisSet;
import pl.coderslab.tennisApi.repository.TennisSetRepository;
import pl.coderslab.tennisApi.service.ResultService;
import pl.coderslab.tennisApi.service.TennisGameService;
import pl.coderslab.tennisApi.service.TennisSetService;

import java.util.List;

@Service
public class TennisSetServiceImpl implements TennisSetService {
    private final TennisSetRepository tennisSetRepository;

    private TennisGameService tennisGameService;
    private ResultService resultService;

    @Autowired
    public TennisSetServiceImpl(TennisSetRepository tennisSetRepository) {
        this.tennisSetRepository = tennisSetRepository;

    }

    @Autowired
    public void setTennisGameService(TennisGameService tennisGameService) {
        this.tennisGameService = tennisGameService;
    }

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }


    @Override
    public TennisSet getOne(int id) {
        return tennisSetRepository.getOne(id);
    }

    @Override
    public List<TennisSet> getAll() {
        return tennisSetRepository.findAll();
    }

    @Override
    public TennisSet save(TennisSet tennisSet) {
        return tennisSetRepository.save(tennisSet);
    }

    @Override
    public void newSetInMatch(Result result) {
        TennisSet tennisSet = new TennisSet();
        tennisSet.setInPlay(true);
//        tennisSet.setResult(result); //TODO it should be made by spring, why not working?
        result.addSet(tennisSet);
        tennisGameService.newGameInCurrentSet(result);
        resultService.save(result);
    }

    @Override
    public void playerWinsGame(Result result, Player winnerOfGame) {
        TennisSet currentTennisSet = resultService.getCurrentSet(result);
        if (winnerOfGame.equals(result.getEvent().getPlayerOne())) {
            currentTennisSet.setGamesWonByPlayerOne(currentTennisSet.getGamesWonByPlayerOne() + 1);
        } else {
            currentTennisSet.setGamesWonByPlayerTwo(currentTennisSet.getGamesWonByPlayerTwo() + 1);
        }

        if (endOfSet(result)) {
            currentTennisSet.setTennisSetWinner(winnerOfGame);
            currentTennisSet.setInPlay(false);
            resultService.playerWinsSet(result, winnerOfGame);
        } else {
            tennisGameService.newGameInCurrentSet(result);
        }

    }

    public long countGamesWonByPlayerOne(Result result) {
        return resultService.getCurrentSet(result).getGames().stream()
                .filter(game -> result.getEvent().getPlayerOne().equals(game.getTennisGameWinner()))
                .count();
    }

    public long countGamesWonByPlayerTwo(Result result) {
        return resultService.getCurrentSet(result).getGames().stream()
                .filter(game -> result.getEvent().getPlayerTwo().equals(game.getTennisGameWinner()))
                .count();
    }

    @Override
    public boolean endOfSet(Result result) {
        return (Math.abs(countGamesWonByPlayerOne(result) - countGamesWonByPlayerTwo(result)) >= 2 &&
                (countGamesWonByPlayerOne(result) >= 6 || countGamesWonByPlayerTwo(result) >= 6));
    }


}
