package task_8_single_table.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("E")
public class Employee extends Person{
    @Column(name = "COMPANY")
    private String company;
    @Column(name = "SALARY")
    private double salary;

    @Builder
    public Employee(Integer id, String name, String surname, String company, double salary) {
        super(id, name, surname);
        this.company = company;
        this.salary = salary;
    }
}
