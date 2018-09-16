package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.*;

import java.util.List;

public interface TennisGameService {
    TennisGame getOne(int id);
    List<TennisGame> getAll();
    TennisGame save(TennisGame tennisGame);

    void playerWinsPoint(Event event, Player winnerOfPoint);
    void newGameInCurrentSet(Event event);
    boolean endOfGame(Event event);
    //    void playerWinsPoint(Result result, TennisSet tennisSet, TennisGame currentGame, Player winnerOfPoint);
//    boolean endOfGame(TennisGame currentGame);
//    void newGameInCurrentSet(TennisSet tennisSet);
}
