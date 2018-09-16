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
    public void newSetInMatch(Event event) {
        TennisSet tennisSet = new TennisSet();
        tennisSet.setInPlay(true);
        event.getResult().addSet(tennisSet);
        tennisGameService.newGameInCurrentSet(event);
        resultService.save(event.getResult());
    }

    @Override
    public void playerWinsGame(Event event, Player winnerOfGame) {
        TennisSet currentTennisSet = resultService.getCurrentSet(event);
        if (winnerOfGame.equals(event.getPlayerOne())) {
            currentTennisSet.setGamesWonByPlayerOne(currentTennisSet.getGamesWonByPlayerOne() + 1);
        } else {
            currentTennisSet.setGamesWonByPlayerTwo(currentTennisSet.getGamesWonByPlayerTwo() + 1);
        }

        if (endOfSet(event)) {
            currentTennisSet.setTennisSetWinner(winnerOfGame);
            currentTennisSet.setInPlay(false);
            resultService.playerWinsSet(event, winnerOfGame);
        } else {
            tennisGameService.newGameInCurrentSet(event);
        }

    }

    public long countGamesWonByPlayerOne(Event event) {
        return resultService.getCurrentSet(event).getGames().stream()
                .filter(game -> event.getPlayerOne().equals(game.getTennisGameWinner()))
                .count();
    }

    public long countGamesWonByPlayerTwo(Event event) {
        return resultService.getCurrentSet(event).getGames().stream()
                .filter(game -> event.getPlayerTwo().equals(game.getTennisGameWinner()))
                .count();
    }

    @Override
    public boolean endOfSet(Event event) {
        return (Math.abs(countGamesWonByPlayerOne(event) - countGamesWonByPlayerTwo(event)) >= 2 &&
                (countGamesWonByPlayerOne(event) >= 6 || countGamesWonByPlayerTwo(event) >= 6));
    }


}
