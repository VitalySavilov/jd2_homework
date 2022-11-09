package by.it.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "t_person_update")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonUpdate implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "PERSON_ID")
    private Long personId;
    @Column(name = "PREVIOUS_NAME")
    private String previousName;
    @Column(name = "NEW_NAME")
    private String newName;
    @Column(name = "UPDATED_BY")
    private String updater;
    @Column(name = "UPDATED_ON")
    private Date updateDate;

}
