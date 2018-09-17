package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.AtpRankingPosition;
import pl.coderslab.tennisApi.entity.Player;

import java.util.List;

public interface PlayerService {
    Player getOne(int id);
    List<Player> getAll();

    Player save(Player player);

}
