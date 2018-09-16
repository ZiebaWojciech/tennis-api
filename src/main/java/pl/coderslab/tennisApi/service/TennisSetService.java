package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.Result;
import pl.coderslab.tennisApi.entity.TennisSet;

import java.util.List;

public interface TennisSetService {
    TennisSet getOne(int id);

    List<TennisSet> getAll();

    TennisSet save(TennisSet tennisSet);

    boolean endOfSet(Event event);
    void playerWinsGame(Event event, Player winnerOfGame);
    void newSetInMatch(Event event);

//    boolean endOfSet(TennisSet currentTennisSet);
//    void playerWinsGame(TennisSet currentTennisSet, Player winnerOfGame);

}
