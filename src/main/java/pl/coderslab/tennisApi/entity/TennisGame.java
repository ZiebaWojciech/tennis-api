package pl.coderslab.tennisApi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TennisGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @ManyToOne
    private Player winner;

    private int winnerPoints;
    private int looserPoints;

}
