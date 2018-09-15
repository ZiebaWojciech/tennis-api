package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.*;

import java.util.List;

public interface ResultService {
    Result getOne(int id);
    List<Result> getAll();
    Result save(Result result);

    Result startEvent(Event event);

    TennisSet getCurrentSet(Result result);
    TennisGame getCurrentGame(Result result);

    void playerOneWinsSet(Result result);

    void playerTwoWinsSet(Result result);

    void playerOneWinsMatch(Result result);
    void playerTwoWinsMatch(Result result);
    //TODO adding games and sets?
}
