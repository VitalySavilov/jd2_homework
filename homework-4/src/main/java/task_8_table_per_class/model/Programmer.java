package task_8_table_per_class.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "t_programmer")
@ToString(callSuper=true)
public class Programmer extends User {
    @Column(name = "LANGUAGE")
    private String language;

    @Builder
    public Programmer(Integer id, String company, String language) {
        super(id, company);
        this.language = language;
    }
}
