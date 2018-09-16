package pl.coderslab.tennisApi.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "atp_ranking")
@Getter
@Data
public class AtpRankingPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;
    @OneToOne
    private Player player;
    private int points;
    private int standing;
}
