package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.*;

import java.util.List;

public interface TennisGameService {
    TennisGame getOne(int id);
    List<TennisGame> getAll();
    TennisGame save(TennisGame tennisGame);

    void playerWinsPoint(Result result, Player winnerOfPoint);
    void newGameInCurrentSet(Result result);
    boolean endOfGame(Result result);
}
