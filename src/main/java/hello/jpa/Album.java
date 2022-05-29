package hello.jpa;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Data
@DiscriminatorValue(value = "A")
public class Album extends Item {
    private String artist;
}
