package my.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_car_model")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "M_ID")
    private Long id;
    @Column(name = "M_MARK")
    private String mark;
    @Column(name = "M_COLOR")
    private String color;
    @Column(name = "M_DATE")
    private Date productionDate;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "I_ID")
    private CarImage image;
}

