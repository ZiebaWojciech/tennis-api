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
        TennisGame game = new TennisGame();
        tennisGameService.save(game);
        TennisSet set = new TennisSet();
        set.addGame(game);
        tennisSetService.save(set);
        Result result = new Result();
        result.setEvent(event);
        result.addSet(set);
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

    @Override
    public void playerOneWinsSet(Result result) {
        Player playerOne = result.getEvent().getPlayerOne();
        getCurrentSet(result).setTennisSetWinner(playerOne);
        long tennisSetWonByPlayerOne = result.getSets().stream()
                                                        .filter(s -> s.getTennisSetWinner().equals(playerOne))
                                                        .count();
        if(tennisSetWonByPlayerOne == 3){
            playerOneWinsMatch(result, playerOne);
        }
    }

    @Override
    public void playerTwoWinsSet(Result result) {
        Player playerTwo = result.getEvent().getPlayerTwo();
        getCurrentSet(result).setTennisSetWinner(playerTwo);
        long tennisSetWonByPlayerOne = result.getSets().stream()
                .filter(s -> s.getTennisSetWinner().equals(playerTwo))
                .count();
        if(tennisSetWonByPlayerOne == 3){
            playerTwoWinsMatch(result, playerTwo);
        }
    }

    private void playerTwoWinsMatch(Result result, Player playerOne) {
        result.setWinner(playerOne);
        result.setLooser(result.getEvent().getPlayerTwo());
    }

    private void playerOneWinsMatch(Result result, Player playerTwo) {
        result.setWinner(playerTwo);
        result.setLooser(result.getEvent().getPlayerOne());
    }


}
