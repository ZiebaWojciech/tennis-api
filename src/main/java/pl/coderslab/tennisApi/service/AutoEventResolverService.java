package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.Player;

public interface AutoEventResolverService {
    Player pointAssign(Event event);
    void setPlayerOneChancesToWin(int changePercentage);
    int chanceRandomize();
}
