package hello.jpa;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

    @Embedded
    private Period period;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(
            name = "favorite_food",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "address",
            joinColumns = @JoinColumn(name = "member_id")
    )
    private List<Address> addressHistory = new ArrayList<>();
}
