package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.enumUtil.EventStatus;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.Result;
import pl.coderslab.tennisApi.service.AutoEventResolverService;
import pl.coderslab.tennisApi.service.EventService;
import pl.coderslab.tennisApi.service.ResultService;

import java.util.Random;

@Service
public class AutoEventResolverServiceImpl implements AutoEventResolverService {
    private int favouritePlayerChances;
    private final EventService eventService;
    private final ResultService resultService;
    private final ThreadPoolTaskScheduler taskScheduler;


    @Autowired
    public AutoEventResolverServiceImpl(EventService eventService, ResultService resultService, ThreadPoolTaskScheduler taskScheduler) {
        this.resultService = resultService;
        this.taskScheduler = taskScheduler;
        this.eventService = eventService;
    }

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
        return randomNumber;
    }

    @Override
    public void setPlayerOneChancesToWin(int changePercentage) {
        this.favouritePlayerChances = changePercentage;
    }
    /**
     * As spring does not allow to use @Scheduled with methods with arguments
     * the event id has to be entered manually for now. The method is only for presentation purposes.
     *
     * @return
     */
    @Scheduled(fixedRate = 200L)
    public void runEventAuto5() {
        Event event = eventService.getOne(5); //TODO << put id here (parametrize)
        Result result = resultService.getOneByEvent(event);
        if (event.getStatus().equals(EventStatus.IN_PROGRESS)) {
            setPlayerOneChancesToWin(90); //TODO << put chances for playerOne to win here (parametrize)
            resultService.playerWinsPointInMatch(result, pointAssign(event));
        }
        if(event.getPlayerOne().equals(EventStatus.COMPLETED)){
            stopEventAuto();
        }
    }
    @Scheduled(fixedRate = 200L)
    public void runEventAuto4() {
        Event event = eventService.getOne(4); //TODO << put id here (parametrize)
        Result result = resultService.getOneByEvent(event);
        if (event.getStatus().equals(EventStatus.IN_PROGRESS)) {
            setPlayerOneChancesToWin(70); //TODO << put chances for playerOne to win here (parametrize)
            resultService.playerWinsPointInMatch(result, pointAssign(event));
        }
        if(event.getPlayerOne().equals(EventStatus.COMPLETED)){
            stopEventAuto();
        }
    }
    @Scheduled(fixedRate = 200L)
    public void runEventAuto3() {
        Event event = eventService.getOne(3); //TODO << put id here (parametrize)
        Result result = resultService.getOneByEvent(event);
        if (event.getStatus().equals(EventStatus.IN_PROGRESS)) {
            setPlayerOneChancesToWin(70); //TODO << put chances for playerOne to win here (parametrize)
            resultService.playerWinsPointInMatch(result, pointAssign(event));
        }
        if(event.getPlayerOne().equals(EventStatus.COMPLETED)){
            stopEventAuto();
        }
    }
    @Scheduled(fixedRate = 200L)
    public void runEventAuto2() {
        Event event = eventService.getOne(2); //TODO << put id here (parametrize)
        Result result = resultService.getOneByEvent(event);
        if (event.getStatus().equals(EventStatus.IN_PROGRESS)) {
            setPlayerOneChancesToWin(10); //TODO << put chances for playerOne to win here (parametrize)
            resultService.playerWinsPointInMatch(result, pointAssign(event));
        }
        if(event.getPlayerOne().equals(EventStatus.COMPLETED)){
            stopEventAuto();
        }
    }





    public void stopEventAuto() {
        taskScheduler.shutdown();
    }
}
