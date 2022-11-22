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
@Table(name = "t_manager")
@ToString(callSuper=true)
public class Manager extends User {
    @Column(name = "PROJECT_NAME")
    private String projectName;

    @Builder
    public Manager(Integer id, String company, String projectName) {
        super(id, company);
        this.projectName = projectName;
    }
}
