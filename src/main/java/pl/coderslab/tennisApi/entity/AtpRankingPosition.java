package pl.coderslab.tennisApi.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "atp_ranking")
@Data
public class AtpRankingPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Player player;
    private LocalDate date;
    private int points;
    private int standing;
}
