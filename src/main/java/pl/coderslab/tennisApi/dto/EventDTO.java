package pl.coderslab.tennisApi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.tennisApi.entity.Country;
import pl.coderslab.tennisApi.entity.EventStatus;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.TennisSet;
import pl.coderslab.tennisApi.service.ResultService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter
@Setter
public class EventDTO {
    private int eventId;
    private Country country;
    private LocalDateTime timeOfStart;
    private Player playerOne;
    private Player playerTwo;
    private EventStatus status;

    private int playerOnePointsInCurrentGame;
    private int playerTwoPointsInCurrentGame;

    private int playerOneWonGamesInSets[] = new int[5];
    private int playerTwoWonGamesInSets[] = new int[5];

    @Autowired
    ResultService resultService;

    public void setPlayerOneWonGamesInSets(int[] playerOneWonGamesInSets) {
//        for(TennisSet tennisSet : sets){

//        }
        this.playerOneWonGamesInSets = playerOneWonGamesInSets;
    }

    public void setPlayerTwoWonGamesInSets(int[] playerTwoWonGamesInSets) {
        this.playerTwoWonGamesInSets = playerTwoWonGamesInSets;
    }
}
