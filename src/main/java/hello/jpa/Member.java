package hello.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    /*@Column(name = "team_id")
    private Long teamId;*/

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
