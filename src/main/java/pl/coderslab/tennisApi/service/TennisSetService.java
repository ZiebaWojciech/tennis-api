package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.TennisSet;

import java.util.List;

public interface TennisSetService {
    TennisSet getOne(int id);
    List<TennisSet> getAll();
    TennisSet save(TennisSet tennisSet);
    //TODO adding games and sets?
}
