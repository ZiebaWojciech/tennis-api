package pl.coderslab.feedingtennisapi.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
public class AtpRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;
    @OneToOne
    private Player player;
    private int points;
    private int standing;
}
