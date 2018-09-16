package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.service.AutoEventResolverService;
import pl.coderslab.tennisApi.service.EventService;

import java.util.Random;

@Service
public class AutoEventResolverServiceImpl implements AutoEventResolverService {
    private final EventService eventService;

    private int favouritePlayerChances;

    @Autowired
    public AutoEventResolverServiceImpl(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Player pointRandomize(Event event) {
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        System.out.println(randomNumber + Thread.currentThread().getName());
        if(randomNumber<favouritePlayerChances){
            return event.getPlayerOne();
        } else {
            return event.getPlayerTwo();
        }

    }

    @Override
    public void setPlayerOneChancesToWin(int changePercentage) {
        this.favouritePlayerChances = changePercentage;
    }

}
