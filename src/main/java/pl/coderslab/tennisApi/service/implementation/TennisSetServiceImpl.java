package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        tennisSet.setResult(result); //TODO it should be made by spring, why not working?
        result.addSet(tennisSet);
        tennisGameService.newGameInCurrentSet(tennisSet);
        resultService.save(result);
    }

    @Override
    public void playerWinsGame(TennisSet currentTennisSet, Player winnerOfGame) {
        if (endOfSet(currentTennisSet)) {
            currentTennisSet.setTennisSetWinner(winnerOfGame);
            currentTennisSet.setInPlay(false);
            resultService.playerWinsSet(currentTennisSet.getResult(), winnerOfGame);
        } else {
            tennisGameService.newGameInCurrentSet(currentTennisSet);
        }

    }

    @Override
    public boolean endOfSet(TennisSet currentTennisSet) {
        return (Math.abs(currentTennisSet.countGamesWonByPlayerOne() - currentTennisSet.countGamesWonByPlayerTwo()) >=2  &&
                (currentTennisSet.countGamesWonByPlayerOne() >= 6 || currentTennisSet.countGamesWonByPlayerTwo() >=6));
    }


}
