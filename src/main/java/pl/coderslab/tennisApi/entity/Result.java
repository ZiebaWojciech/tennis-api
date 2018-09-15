package pl.coderslab.tennisApi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Event event;

    @OneToMany
    private List<TennisSet> sets = new ArrayList<>();
    @ManyToOne
    private Player winner;
    @ManyToOne
    private Player looser;


}

