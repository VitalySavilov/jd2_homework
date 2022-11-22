package task_6.model;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_course")
@Builder
public class Course implements Serializable, BaseEntity<Integer> {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "COURSE_NAME")
    private String courseName;
    @Column(name = "PRICE")
    private double price;
}
