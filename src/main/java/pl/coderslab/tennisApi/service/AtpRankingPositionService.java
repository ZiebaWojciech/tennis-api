package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.AtpRankingPosition;
import pl.coderslab.tennisApi.entity.Player;

import java.time.LocalDate;
import java.util.List;

public interface AtpRankingPositionService {
    AtpRankingPosition getOne(int id);
    List<AtpRankingPosition> getAllByPlayerId(int id);
    List<AtpRankingPosition> getAllByTime(LocalDate date);
    AtpRankingPosition save(AtpRankingPosition atpRankingPosition);
}
