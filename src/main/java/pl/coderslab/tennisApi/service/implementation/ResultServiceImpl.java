package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.*;
import pl.coderslab.tennisApi.repository.ResultRepository;
import pl.coderslab.tennisApi.service.PlayerService;
import pl.coderslab.tennisApi.service.ResultService;
import pl.coderslab.tennisApi.service.TennisGameService;
import pl.coderslab.tennisApi.service.TennisSetService;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;

    private TennisGameService tennisGameService;
    private TennisSetService tennisSetService;
    private PlayerService playerService;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Autowired
    public void setTennisGameService(TennisGameService tennisGameService) {
        this.tennisGameService = tennisGameService;
    }

    @Autowired
    public void setTennisSetService(TennisSetService tennisSetService) {
        this.tennisSetService = tennisSetService;
    }

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public Result getOne(int id) {
        return resultRepository.getOne(id);
    }

    @Override
    public Result getOneByEvent(Event event) {
        return resultRepository.getByEvent(event);
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
    public Result startResultOfEvent(Event event) {
        Result result = new Result();
        result.setEvent(event);
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

    @Override
    public void playerWinsPointInMatch(Result result, Player winnerOfPoint) {
        tennisGameService.playerWinsPoint(result, winnerOfPoint);
    }

    @Override
    public void playerWinsSet(Result result, Player winnerOfSet) {
        if (winnerOfSet.equals(result.getEvent().getPlayerOne())) {
            result.setSetsWonByPlayerOne(result.getSetsWonByPlayerOne() + 1);
        } else {
            result.setSetsWonByPlayerTwo(result.getSetsWonByPlayerTwo() + 1);
        }
        if (result.getSets().stream().filter(s -> s.getTennisSetWinner().equals(winnerOfSet)).count() == 3) {
            endOfMatch(result, winnerOfSet);
        } else {
            tennisSetService.newSetInMatch(result);
        }
    }

    @Override
    public void endOfMatch(Result result, Player winnerOfSet) {
        Player looser = result.getEvent().getPlayerOne().equals(winnerOfSet) ? result.getEvent().getPlayerTwo() : result.getEvent().getPlayerOne();
        result.setLooser(looser);
        result.setWinner(winnerOfSet);
        result.getEvent().setStatus(EventStatus.COMPLETED);
        save(result);
    }



}
