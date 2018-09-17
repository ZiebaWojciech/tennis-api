package pl.coderslab.tennisApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.tennisApi.entity.AtpRankingPosition;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.enumUtil.Country;
import pl.coderslab.tennisApi.service.PlayerService;

import javax.persistence.*;
import java.sql.Date;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

//@Data
//public class PlayerDTO {
//    private int id;
//    private String name;
//    private String surname;
//    private Country countryCode;
//    private Date birthday;
//    private AtpRankingPosition currentAtpRankingPositions;
//
//    public PlayerDTO(Player player, List<AtpRankingPosition> atpRankingPosition) {
//        this.id = player.getId();
//        this.name = player.getName();
//        this.surname = player.getSurname();
//        this.countryCode = player.getCountryCode();
//        this.birthday = player.getBirthday();
//        this.currentAtpRankingPositions = atpRankingPosition.stream().min((first, second) -> Period.between(first.getDate(), second.getDate()).getDays()).orElse(null);
//    }
//
//}
