package my.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_car_image")
public class CarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_ID")
    private Long id;
    @Lob
    @Column(name = "I_IMAGE", columnDefinition = "MEDIUMBLOB")
    private byte[] image;
}

