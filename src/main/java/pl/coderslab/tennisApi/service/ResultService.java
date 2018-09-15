package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.Result;

import java.util.List;

public interface ResultService {
    Result getOne(int id);
    List<Result> getAll();
    Result save(Result result);
    //TODO adding games and sets?
}
