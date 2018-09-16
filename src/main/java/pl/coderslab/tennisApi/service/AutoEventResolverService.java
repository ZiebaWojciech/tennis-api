package pl.coderslab.tennisApi.service;

import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.Player;

public interface AutoEventResolverService {
    Player pointRandomize(Event event);
    void setPlayerOneChancesToWin(int changePercentage);
}
