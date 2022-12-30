package hello.jpa;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private Integer price;
}
