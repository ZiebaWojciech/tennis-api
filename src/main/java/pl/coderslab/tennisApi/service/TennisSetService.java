package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.Result;
import pl.coderslab.tennisApi.entity.TennisSet;

import java.util.List;

public interface TennisSetService {
    TennisSet getOne(int id);

    List<TennisSet> getAll();

    TennisSet save(TennisSet tennisSet);

//    void playerOneWinsGame(Result result);
//
//    void playerTwoWinsGame(Result result);

    void newSetInMatch(Result result);
    boolean endOfSet(TennisSet currentTennisSet);
    void playerWinsGame(TennisSet currentTennisSet, Player winnerOfGame);

    //TODO adding games and sets?
}
