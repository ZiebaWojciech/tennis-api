package pl.coderslab.tennisApi.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
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
