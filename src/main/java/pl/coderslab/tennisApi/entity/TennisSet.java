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

    @ManyToOne
    Result result;

    @OneToMany
    private List<TennisGame> games = new ArrayList<>();

    private boolean inPlay;

    private int gamesWonByPlayerOne;
    private int gamesWonByPlayerTwo;

    @ManyToOne
    private Player tennisSetWinner;

    public void addGame(TennisGame tennisGame){
        this.games.add(tennisGame);
    }
}
