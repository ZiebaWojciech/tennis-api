package pl.coderslab.tennisApi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.tennisApi.entity.Country;
import pl.coderslab.tennisApi.entity.EventStatus;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.TennisSet;
import pl.coderslab.tennisApi.service.ResultService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter
@Setter
@Data
public class EventDTO {
    private int eventId;
    private Country country;
    private LocalDateTime timeOfStart;
    private Player playerOne;
    private Player playerTwo;
    private EventStatus status;

    private List<TennisSet> sets;
}
