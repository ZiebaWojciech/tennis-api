package pl.coderslab.tennisApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.tennisApi.entity.enumUtil.Country;
import pl.coderslab.tennisApi.entity.enumUtil.EventStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Country country;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeOfStart;
    @NotNull
    @ManyToOne
    private Player playerOne;
    @NotNull
    @ManyToOne
    private Player playerTwo;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EventStatus status;
}
