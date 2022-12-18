package my.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_ID")
    private Long id;
    @Column(name = "C_PRICE")
    private Long price;
    @Column(name = "C_TYPE")
    @Enumerated(EnumType.STRING)
    private CarType type;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "M_ID")
    private CarModel carModel;
}

