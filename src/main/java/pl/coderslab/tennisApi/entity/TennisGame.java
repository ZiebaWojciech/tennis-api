package pl.coderslab.tennisApi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TennisGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    private int playerOnePoints;
    private int playerTwoPoints;

}
