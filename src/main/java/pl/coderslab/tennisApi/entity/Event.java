package pl.coderslab.tennisApi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDateTime timeOfStart;
    @NotNull
    private Country country;
    @NotNull
    @ManyToOne
    private Player playerOne;
    @NotNull
    @ManyToOne
    private Player playerTwo;
    @NotNull
    private EventStatus status;
    @NotNull
    @OneToOne(mappedBy = "event")
    private Result result;
}
