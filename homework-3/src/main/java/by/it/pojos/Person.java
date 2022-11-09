package by.it.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_person")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "t_person_seq", allocationSize = 1)
    private Long id;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "FIRSTNAME")
    private String name;
    @Column(name = "LASTNAME")
    private String surname;
}
