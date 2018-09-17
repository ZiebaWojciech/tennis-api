package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.*;
import pl.coderslab.tennisApi.repository.ResultRepository;
import pl.coderslab.tennisApi.service.*;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;

    private TennisGameService tennisGameService;
    private TennisSetService tennisSetService;
    private EventService eventService;

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
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
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
    public TennisSet getCurrentSet(Event event) {
        return event.getResult().getSets().stream()
                .filter(TennisSet::isInPlay)
                .findAny()
                .orElse(null);
    }

    @Override
    public TennisGame getCurrentGame(Event event) {
        return getCurrentSet(event).getGames().stream()
                .filter(TennisGame::isInPlay)
                .findAny()
                .orElse(null);
    }

    @Override
    public void playerWinsPointInMatch(Event event, Player winnerOfPoint) {
        tennisGameService.playerWinsPoint(event, winnerOfPoint);
    }

    @Override
    public void playerWinsSet(Event event, Player winnerOfSet) {
        if (winnerOfSet.equals(event.getPlayerOne())) {
            event.getResult().setSetsWonByPlayerOne(event.getResult().getSetsWonByPlayerOne() + 1);
        } else {
            event.getResult().setSetsWonByPlayerTwo(event.getResult().getSetsWonByPlayerTwo() + 1);
        }
        if (event.getResult().getSets().stream().filter(s -> s.getTennisSetWinner().equals(winnerOfSet)).count() == 3) {
            eventService.endOfMatch(event, winnerOfSet);
        } else {
            tennisSetService.newSetInMatch(event);
        }
    }


//    @Override
//    public void endOfMatch(Event event, Player winnerOfSet) {
//        Player looser = event.getPlayerOne().equals(winnerOfSet) ? event.getPlayerTwo() : event.getPlayerOne();
//        event.getResult().setLooser(looser);
//        event.getResult().setWinner(winnerOfSet);
//        event.setStatus(EventStatus.COMPLETED);
//        save(event.getResult());
//    }



}
