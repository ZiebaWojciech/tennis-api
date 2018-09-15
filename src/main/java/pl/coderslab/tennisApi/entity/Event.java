package pl.coderslab.tennisApi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime timeOfStart;
    private Country country;
    @ManyToOne
    private Player playerOne;
    @ManyToOne
    private Player playerTwo;
    private EventStatus status;
    @OneToOne(mappedBy = "event")
    private Results results;
}
