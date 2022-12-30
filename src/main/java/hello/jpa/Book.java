package hello.jpa;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Data
@DiscriminatorValue(value = "B")
public class Book extends Item {
    private String author;
    private String isbn;
}
