package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.Result;
import pl.coderslab.tennisApi.entity.TennisGame;
import pl.coderslab.tennisApi.entity.TennisSet;

import java.util.List;

public interface TennisGameService {
    TennisGame getOne(int id);
    List<TennisGame> getAll();
    TennisGame save(TennisGame tennisGame);

    void playerOneWinsPoint(Result result);
    void playerTwoWinsPoint(Result result);
}
