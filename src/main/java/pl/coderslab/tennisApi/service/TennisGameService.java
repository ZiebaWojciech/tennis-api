package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.TennisGame;

import java.util.List;

public interface TennisGameService {
    TennisGame getOne(int id);
    List<TennisGame> getAll();
    TennisGame save(TennisGame tennisGame);
}
