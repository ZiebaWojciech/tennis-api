package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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

    private final TennisGameService tennisGameService;
    private final ResultService resultService;

    @Autowired
    public TennisSetServiceImpl(TennisSetRepository tennisSetRepository, ResultService resultService, TennisGameService tennisGameService) {
        this.tennisSetRepository = tennisSetRepository;
        this.resultService = resultService;
        this.tennisGameService = tennisGameService;
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



//    @Override
//    public void playerOneWinsGame(Result result) {
//        resultService.getCurrentGame(result).setTennisGameWinner(result.getEvent().getPlayerOne());
//        TennisSet currentSet = resultService.getCurrentSet(result);
//        currentSet.setGamesWonByPlayerOne(currentSet.getGamesWonByPlayerOne()+1);
//        if(currentSet.getGamesWonByPlayerOne()-currentSet.getGamesWonByPlayerTwo() == 2 && currentSet.getGamesWonByPlayerOne() >=6){
//            resultService.playerOneWinsSet(result);
//        }
//
//    }
//
//    @Override
//    public void playerTwoWinsGame(Result result) {
//        TennisSet currentSet = resultService.getCurrentSet(result);
//        currentSet.setGamesWonByPlayerTwo(currentSet.getGamesWonByPlayerTwo()+1);
//        if(currentSet.getGamesWonByPlayerTwo()-currentSet.getGamesWonByPlayerOne() == 2 && currentSet.getGamesWonByPlayerTwo() >=6){
//            resultService.playerTwoWinsSet(result);
//        }
//    }

    @Override
    public void newSetInMatch(Result result) {
        TennisSet tennisSet = new TennisSet();
        tennisSet.setInPlay(true);
        result.addSet(tennisSet);
        tennisGameService.newGameInCurrentSet(tennisSet);
        resultService.save(result);
    }

    @Override
    public void playerWinsGame(TennisSet currentTennisSet, Player winnerOfGame) {
        if(endOfSet(currentTennisSet)){
            currentTennisSet.setTennisSetWinner(winnerOfGame);
            resultService.playerWinsSet(currentTennisSet.getResult(), winnerOfGame);
        }
    }
    @Override
    public boolean endOfSet(TennisSet currentTennisSet) {
        return (currentTennisSet.getGamesWonByPlayerOne()-currentTennisSet.getGamesWonByPlayerTwo() == 2 && currentTennisSet.getGamesWonByPlayerOne() >=6);
    }

}
