package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.*;

import java.util.List;

public interface ResultService {
    Result getOne(int id);
    List<Result> getAll();
    Result save(Result result);

    TennisSet getCurrentSet(Event event);
    TennisGame getCurrentGame(Event event);


    void playerWinsSet(Event event, Player winnerOfSet);
    void playerWinsPointInMatch(Event event, Player winnerOfPoint);
    void endOfMatch(Event event, Player winnerOfLastSet);
}
