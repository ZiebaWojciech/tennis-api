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

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<TennisGame> games = new ArrayList<>();

    private boolean inPlay;

    @ManyToOne
    private Player tennisSetWinner;

    public void addGame(TennisGame tennisGame){
        this.games.add(tennisGame);
    }
}
