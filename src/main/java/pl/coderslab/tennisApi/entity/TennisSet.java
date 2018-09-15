package pl.coderslab.tennisApi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class TennisSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<TennisGame> games;

    @ManyToOne
    private Player winner;
}
