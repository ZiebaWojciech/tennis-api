package pl.coderslab.feedingtennisapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Getter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Country countryCode;
    private Date birthday;

}
