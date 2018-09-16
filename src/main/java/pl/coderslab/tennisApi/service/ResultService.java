package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.*;

import java.util.List;

public interface ResultService {
    Result getOne(int id);
    Result getOneByEvent(Event event);
    List<Result> getAll();
    Result save(Result result);

    Result startEvent(Event event);

    TennisSet getCurrentSet(Result result);
    TennisGame getCurrentGame(Result result);


    void playerWinsSet(Result result, Player winnerOfSet);
    void playerWinsPointInMatch(Result result, Player winnerOfPoint);
    boolean endOfMatch(Result result, Player winnerOfLastSet);
    //TODO adding games and sets?
}
