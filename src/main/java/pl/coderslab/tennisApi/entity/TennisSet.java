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

    @ManyToOne(cascade = {CascadeType.ALL})
    Result result;

    private int sequenceNumber;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<TennisGame> games = new ArrayList<>();

    private boolean inPlay;

    @ManyToOne
    private Player tennisSetWinner;

    public void addGame(TennisGame tennisGame){
        this.games.add(tennisGame);
    }

    public long countGamesWonByPlayerOne() {
        return this.games.stream()
                .filter(game -> result.getEvent().getPlayerOne().equals(game.getTennisGameWinner()))
                .count();
    }

    public long countGamesWonByPlayerTwo() {
        return this.games.stream()
                .filter(game -> result.getEvent().getPlayerTwo().equals(game.getTennisGameWinner()))
                .count();
    }
}
