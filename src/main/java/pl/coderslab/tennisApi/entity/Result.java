package pl.coderslab.tennisApi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Event event;

    @OneToMany(mappedBy = "result", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<TennisSet> sets = new ArrayList<>();
    @ManyToOne
    private Player winner; //TODO probably will be redundant
    @ManyToOne
    private Player looser;

    public void addSet(TennisSet tennisSet){
        this.sets.add(tennisSet);
    }
}

