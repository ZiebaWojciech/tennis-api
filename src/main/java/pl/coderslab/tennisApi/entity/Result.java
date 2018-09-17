package pl.coderslab.tennisApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<TennisSet> sets = new ArrayList<>();
    @ManyToOne
    private Player winner;
    @ManyToOne
    private Player looser;
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Event event;

    private int setsWonByPlayerOne;
    private int setsWonByPlayerTwo;

//    @JsonIgnore
    private int tennisSetInMatchSequentNumber = 1;

    public void addSet(TennisSet tennisSet){
        tennisSet.setSequenceNumber(tennisSetInMatchSequentNumber++);
        this.sets.add(tennisSet);

    }


}

