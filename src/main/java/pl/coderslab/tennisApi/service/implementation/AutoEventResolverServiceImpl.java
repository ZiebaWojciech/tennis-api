package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.service.AutoEventResolverService;

import java.util.Random;

@Service
public class AutoEventResolverServiceImpl implements AutoEventResolverService {
    private int favouritePlayerChances;

    @Override
    public Player pointAssign(Event event) {
        int randomNumber = chanceRandomize();
        if (randomNumber < favouritePlayerChances) {
            return event.getPlayerOne();
        } else {
            return event.getPlayerTwo();
        }
    }

    @Override
    public int chanceRandomize() {
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        System.out.println(randomNumber + Thread.currentThread().getName());
        return randomNumber;
    }

    @Override
    public void setPlayerOneChancesToWin(int changePercentage) {
        this.favouritePlayerChances = changePercentage;
    }

}
