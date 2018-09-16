package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.*;

import java.util.List;

public interface ResultService {
    Result getOne(int id);
    Result getOneByEvent(Event event);
    List<Result> getAll();
    Result save(Result result);

    Result startResultOfEvent(Event event);

    TennisSet getCurrentSet(Result result);
    TennisGame getCurrentGame(Result result);


    void playerWinsSet(Result result, Player winnerOfSet);
    void playerWinsPointInMatch(Result result, Player winnerOfPoint);
    void endOfMatch(Result result, Player winnerOfLastSet);
}
