package pl.coderslab.tennisApi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class TennisSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne(cascade = {CascadeType.ALL}) //TODO shall not be here, but now is used
//    Result result;

    private int sequenceNumber;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<TennisGame> games = new ArrayList<>();

    private int gamesWonByPlayerOne;
    private int gamesWonByPlayerTwo;

    private boolean inPlay;

    @ManyToOne
    private Player tennisSetWinner;

    public void addGame(TennisGame tennisGame){
        this.games.add(tennisGame);
    }

}
